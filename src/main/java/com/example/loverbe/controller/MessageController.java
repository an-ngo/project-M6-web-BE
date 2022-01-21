package com.example.loverbe.controller;

import com.example.loverbe.model.entity.room.Message;
import com.example.loverbe.model.entity.room.Room;
import com.example.loverbe.service.IMessageService;
import com.example.loverbe.service.Implement.MessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@RestController
@CrossOrigin("*")
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private IMessageService messageService;

    private Map<Long, SseEmitter> emitterMap = new HashMap<>();
    private SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);

    @RequestMapping(value = "/subscribe", consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe(@RequestParam Long roomId){
        sendInitEvent(sseEmitter);
        emitterMap.put(roomId, sseEmitter);
        sseEmitter.onCompletion(() -> emitterMap.remove(sseEmitter));
        sseEmitter.onTimeout(() -> emitterMap.remove(sseEmitter));
        sseEmitter.onError((e) -> emitterMap.remove(sseEmitter));
        return sseEmitter;
    }
    private void sendInitEvent(SseEmitter sseEmitter){
        try {
            sseEmitter.send(SseEmitter.event().name("INIT"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping
    public void dispatchEventToClients(@RequestBody Message message, @RequestParam Long roomId){
        message.setTimeSend(LocalDate.now());
        SseEmitter sseEmitter = emitterMap.get(roomId);
        if (sseEmitter != null){
            try {
                sseEmitter.send(message);
            } catch (IOException e) {
                emitterMap.remove(sseEmitter);
            }
        }
    }
    @PostMapping("/room")
    public ResponseEntity<Iterable<Message>> getByRoom(@RequestBody Room room){
        return new ResponseEntity<>(messageService.findAllByRoom(room), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Iterable<Message>> getAllMessage(){
        return new ResponseEntity<>(messageService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable Long id) {
        Optional<Message> messageOptional = messageService.findById(id);
        return messageOptional.map(message -> new ResponseEntity<>(message, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateRoom(@PathVariable Long id, @RequestBody Message message){
        Optional<Message> messageOptional = messageService.findById(id);
        if (!messageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (message.getId() == null) {
                message.setId(id);
            }
            messageService.save(message);
            return new ResponseEntity<>(messageOptional.get(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteRoom(@PathVariable Long id) {
        Optional<Message> messageOptional = messageService.findById(id);
        if (!messageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            messageService.remove(id);
            return new ResponseEntity<>(messageOptional.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Message> createCustomer(@RequestBody Message message) {
        message.setTimeSend(LocalDate.now());
        return new ResponseEntity<>(messageService.save(message) ,HttpStatus.CREATED);
    }
}
