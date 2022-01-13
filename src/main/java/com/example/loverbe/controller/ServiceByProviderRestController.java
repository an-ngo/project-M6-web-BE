package com.example.loverbe.controller;

import com.example.loverbe.model.entity.user.nccdv.ServiceByProvider;
import com.example.loverbe.service.IServiceByNCCDVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/serviceByProvider")
public class ServiceByProviderRestController {
    @Autowired
    IServiceByNCCDVService serviceByNCCDVService;
    @GetMapping
    public ResponseEntity<Iterable<ServiceByProvider>>showAll(){
        return new ResponseEntity<>(serviceByNCCDVService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ServiceByProvider>showById(@PathVariable Long id, @RequestBody ServiceByProvider serviceByProvider){
       Optional<ServiceByProvider> service = serviceByNCCDVService.findById(id);
       if (!service.isPresent()){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       else
           return new ResponseEntity<>(service.get(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ServiceByProvider>create(@RequestBody ServiceByProvider serviceByProvider){
        return new ResponseEntity<>(serviceByNCCDVService.save(serviceByProvider),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ServiceByProvider>edit(@PathVariable Long id, @RequestBody ServiceByProvider serviceByProvider){
        Optional<ServiceByProvider>service = serviceByNCCDVService.findById(id);
        if (!service.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            if(serviceByProvider.getId()==null){
                serviceByProvider.setId(id);
            }
            serviceByNCCDVService.save(serviceByProvider);
        return new ResponseEntity<>(serviceByProvider,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceByProvider>delete(@PathVariable Long id){
        Optional<ServiceByProvider> service = serviceByNCCDVService.findById(id);
        if (!service.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else
            serviceByNCCDVService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
