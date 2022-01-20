package com.example.loverbe.controller;

import com.example.loverbe.model.entity.room.Room;
import com.example.loverbe.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/rooms")
public class RoomController {
    @Autowired private IRoomService roomService;
    @GetMapping
    public ResponseEntity<Iterable<Room>> getAllRoom(){
        return new ResponseEntity<>(roomService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable Long id) {
        Optional<Room> roomOptional = roomService.findById(id);
        return roomOptional.map(room -> new ResponseEntity<>(room, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room room){
        Optional<Room> roomOptional = roomService.findById(id);
        if (!roomOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (room.getId() == null) {
                room.setId(id);
            }
            roomService.save(room);
            return new ResponseEntity<>(roomOptional.get(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Room> deleteRoom(@PathVariable Long id) {
        Optional<Room> roomOptional = roomService.findById(id);
        if (!roomOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            roomService.remove(id);
            return new ResponseEntity<>(roomOptional.get(), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Room> createCustomer(@RequestBody Room room) {
        return new ResponseEntity<>(roomService.save(room) ,HttpStatus.CREATED);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<Iterable<Room>> findByUserId(@PathVariable Long id){
        return new ResponseEntity<>(roomService.findAllByUserId(id), HttpStatus.OK);
    }
}
