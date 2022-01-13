package com.example.loverbe.controller;

import com.example.loverbe.model.entity.user.User;
import com.example.loverbe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminProvider {
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<Page<User>> findAll(@PageableDefault(value = 5) Pageable pageable){
        return new ResponseEntity<>(userService.findAllUser(pageable), HttpStatus.OK);
    }
    @PutMapping("/{id}/{statusProvider}")
    public ResponseEntity<User> changeStatusProvider(@PathVariable String statusProvider, @PathVariable Long id){
        Optional<User> currentUser = userService.findById(id);
        if (!currentUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentUser.get().setIsStatusProvider(statusProvider);
        return new ResponseEntity<>(userService.save(currentUser.get()), HttpStatus.ACCEPTED);
    }
}
