package com.example.loverbe.controller;

import com.example.loverbe.model.entity.room.Message;
import com.example.loverbe.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/messages")
public class MessageController {
@Autowired private IMessageService messageService;
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
        messageService.save(message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
