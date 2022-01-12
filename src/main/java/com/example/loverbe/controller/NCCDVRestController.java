package com.example.loverbe.controller;

import com.example.loverbe.model.entity.user.NCCDV;
import com.example.loverbe.model.entity.user.User;
import com.example.loverbe.service.INCCDVService;
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
@RequestMapping("/nccdv")
public class NCCDVRestController {
    @Autowired
    private INCCDVService nccdvService;
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<Page<NCCDV>> findAll(@PageableDefault(value = 5)Pageable pageable){
        return new ResponseEntity<>(nccdvService.findAllPage(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<NCCDV> findById(@PathVariable Long id){
        Optional<NCCDV> nccdv = nccdvService.findById(id);
        if (!nccdv.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(nccdv.get(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<NCCDV> save(@RequestBody NCCDV nccdv){
        return new ResponseEntity<>(nccdvService.save(nccdv), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<NCCDV> edit(@RequestBody NCCDV nccdv){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> user = userService.findByUsername(username);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<NCCDV> currentNCCDV = nccdvService.findByUser(user.get());
        if (!currentNCCDV.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        nccdv.setId(currentNCCDV.get().getId());
        return new ResponseEntity<>(nccdvService.save(nccdv), HttpStatus.ACCEPTED);
    }
    @DeleteMapping
    public ResponseEntity<?> delete(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> user = userService.findByUsername(username);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<NCCDV> currentNCCDV = nccdvService.findByUser(user.get());
        if (!currentNCCDV.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        nccdvService.remove(currentNCCDV.get().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
