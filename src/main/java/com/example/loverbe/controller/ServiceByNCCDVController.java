package com.example.loverbe.controller;

import com.example.loverbe.model.entity.order.Orders;
import com.example.loverbe.model.entity.user.nccdv.ServiceByNCCDV;
import com.example.loverbe.service.IServiceByNCCDVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/serviceByNCCDV")
public class ServiceByNCCDVController {
    @Autowired
    IServiceByNCCDVService serviceByNCCDVService;
    @GetMapping
    public ResponseEntity<Iterable<ServiceByNCCDV>>showAll(){
        return new ResponseEntity<>(serviceByNCCDVService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ServiceByNCCDV>showById(@PathVariable Long id,@RequestBody ServiceByNCCDV serviceByNCCDV){
       Optional<ServiceByNCCDV> service = serviceByNCCDVService.findById(id);
       if (!service.isPresent()){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       else
           return new ResponseEntity<>(service.get(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ServiceByNCCDV>create(@RequestBody ServiceByNCCDV serviceByNCCDV){
        return new ResponseEntity<>(serviceByNCCDVService.save(serviceByNCCDV),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ServiceByNCCDV>edit(@PathVariable Long id,@RequestBody ServiceByNCCDV serviceByNCCDV){
        Optional<ServiceByNCCDV>service = serviceByNCCDVService.findById(id);
        if (!service.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            if(serviceByNCCDV.getId()==null){
                serviceByNCCDV.setId(id);
            }
            serviceByNCCDVService.save(serviceByNCCDV);
        return new ResponseEntity<>(service.get(),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceByNCCDV>delete(@PathVariable Long id){
        Optional<ServiceByNCCDV> service = serviceByNCCDVService.findById(id);
        if (!service.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else
            serviceByNCCDVService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
