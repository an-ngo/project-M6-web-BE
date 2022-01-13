package com.example.loverbe.controller;

import com.example.loverbe.model.entity.order.Orders;
import com.example.loverbe.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ord")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @GetMapping("/all")
    public ResponseEntity<Iterable<Orders>> showAll(){
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Orders>showById(@PathVariable Long id){
        Optional<Orders>orders=orderService.findById(id);
        if (!orders.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity<>(orders.get(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Orders>create(@RequestBody Orders orders){
        return new ResponseEntity<>(orderService.save(orders),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Orders>edit(@PathVariable Long id,@RequestBody Orders orders){
        Optional<Orders> orders1 = orderService.findById(id);
        if (!orders1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else
            if (orders.getId()==null){
                orders.setId(id);
            }
        orderService.save(orders);
            return new ResponseEntity<>(orders,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Orders>delete(@PathVariable Long id){
        Optional<Orders> orders = orderService.findById(id);
        if (!orders.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else
            orderService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
}
