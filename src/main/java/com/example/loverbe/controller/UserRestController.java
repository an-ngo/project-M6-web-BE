package com.example.loverbe.controller;

import com.example.loverbe.model.dto.request.UserEditForm;
import com.example.loverbe.model.entity.user.User;
import com.example.loverbe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public ResponseEntity<Page<User>> findAllServiceProvider(@PageableDefault(value = 5) Pageable pageable){
        return new ResponseEntity<>(userService.findAllServiceProvider(pageable), HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<User> showInfo(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> currentUser = userService.findByUsername(username);
        if (!currentUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentUser.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.get().setViewCount(user.get().getViewCount() + 1);
        return new ResponseEntity<>(userService.save(user.get()), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<User> editUser(@RequestBody UserEditForm userEditForm){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> currentUser = userService.findByUsername(username);
        if (!currentUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = currentUser.get();
        user.setName(userEditForm.getName());
        user.setPhone(userEditForm.getPhone());
        user.setGender(userEditForm.getGender());
        user.setAvatar(userEditForm.getAvatar());
        return new ResponseEntity<>(userService.save(user), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> currentUser = userService.findByUsername(username);
        if (!currentUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.remove(currentUser.get().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
