package com.example.loverbe.controller;

import com.example.loverbe.model.entity.user.User;
import com.example.loverbe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/maps")
public class MapController {
    @Autowired
    IUserService userService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        User currentUser = userService.findByUsername(username).get();
        List<User> users = userService.findAllByLongitudeBetweenAndLatitudeBetween(currentUser.getLongitude()-0.5, currentUser.getLongitude() +0.5, currentUser.getLatitude() -0.5, currentUser.getLatitude()+0.5 );
//        users.remove(currentUser);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
