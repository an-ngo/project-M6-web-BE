package com.example.loverbe.controller;

import com.example.loverbe.model.entity.user.nccdv.ServiceByProvider;
import com.example.loverbe.service.IServiceByProviderService;
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
    IServiceByProviderService serviceByProviderService;
    @GetMapping
    public ResponseEntity<Iterable<ServiceByProvider>>showAll(){
        return new ResponseEntity<>(serviceByProviderService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ServiceByProvider>showById(@PathVariable Long id, @RequestBody ServiceByProvider serviceByProvider){
       Optional<ServiceByProvider> service = serviceByProviderService.findById(id);
       if (!service.isPresent()){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       else
           return new ResponseEntity<>(service.get(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ServiceByProvider>create(@RequestBody ServiceByProvider serviceByProvider){
        return new ResponseEntity<>(serviceByProviderService.save(serviceByProvider),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ServiceByProvider>edit(@PathVariable Long id, @RequestBody ServiceByProvider serviceByProvider){
        Optional<ServiceByProvider>service = serviceByProviderService.findById(id);
        if (!service.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            if(serviceByProvider.getId()==null){
                serviceByProvider.setId(id);
            }
            serviceByProviderService.save(serviceByProvider);
        return new ResponseEntity<>(serviceByProvider,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceByProvider>delete(@PathVariable Long id){
        Optional<ServiceByProvider> service = serviceByProviderService.findById(id);
        if (!service.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else
            serviceByProviderService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
