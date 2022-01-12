package com.example.loverbe.controller;

import com.example.loverbe.model.entity.user.nccdv.Image;
import com.example.loverbe.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/image")
public class ImageRestController {
    @Autowired
    private IImageService iImageService;

    @GetMapping
    public ResponseEntity<Iterable<Image>> findAll(){
        return new ResponseEntity<>(iImageService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Image> findById(@PathVariable Long id){
        Optional<Image> image = iImageService.findById(id);
        if (!image.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(image.get(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Image> save(@RequestBody Image image){
        return new ResponseEntity<>(iImageService.save(image), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Image> edit(@PathVariable Long id, @RequestBody Image image){
        Optional<Image> currentImage = iImageService.findById(id);
        if (!currentImage.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        image.setId(id);
        return new ResponseEntity<>(iImageService.save(image), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        iImageService.remove(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("user/{userId}")
    public ResponseEntity<Iterable<Image>> findByUserID(@PathVariable Long userId){
        return new ResponseEntity<>(iImageService.findAllByUserId(userId), HttpStatus.OK);
    }
}
