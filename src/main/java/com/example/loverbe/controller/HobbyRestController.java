package com.example.loverbe.controller;

import com.example.loverbe.model.entity.user.nccdv.Hobby;
import com.example.loverbe.service.IHobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/hobby")
public class HobbyRestController {
    @Autowired
    private IHobbyService hobbyService;

    @GetMapping
    public ResponseEntity<Iterable<Hobby>> findAll(){
        return new ResponseEntity<>(hobbyService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Hobby> findById(@PathVariable Long id){
        Optional<Hobby> hobby = hobbyService.findById(id);
        if (!hobby.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hobby.get(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Hobby> save(@RequestBody Hobby hobby){
        return new ResponseEntity<>(hobbyService.save(hobby), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Hobby> edit(@PathVariable Long id, @RequestBody Hobby hobby){
        Optional<Hobby> currentHobby = hobbyService.findById(id);
        if (!currentHobby.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        hobby.setId(id);
        return new ResponseEntity<>(hobbyService.save(hobby), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Hobby> currentHobby = hobbyService.findById(id);
        if (!currentHobby.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        hobbyService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
