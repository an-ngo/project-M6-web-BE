package com.example.loverbe.controller;


import com.example.loverbe.enums.EnumOrder;
import com.example.loverbe.model.dto.request.OrderForm;
import com.example.loverbe.model.entity.orders.Orders;
import com.example.loverbe.model.entity.user.User;
import com.example.loverbe.model.entity.user.nccdv.ServiceByProvider;
import com.example.loverbe.service.IOrdersService;
import com.example.loverbe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrdersController {
    @Autowired
    private IOrdersService orderService;

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Orders>> showAll(){
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Orders>showById(@PathVariable Long id){
        Optional<Orders> orders = orderService.findById(id);
        if (!orders.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity<>(orders.get(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Orders>create(@RequestBody OrderForm orderForm){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> currentUser = userService.findByUsername(username);
        if (!currentUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Orders orders = new Orders();
        orders.setUserProvider(orderForm.getUserProvider());
        orders.setUser(currentUser.get());
        orders.setPlace(orderForm.getPlace());
        orders.setDuration(orderForm.getDuration());
        orders.setDate(new Date(orderForm.getDate()));
        orders.setStatusOrder("PENDING");
        List<String> serviceList = orderForm.getServiceByProviderList();
        double money = 0;
        int count = 0;
        if (!serviceList.isEmpty()){
            orders.setServiceByProviderList(new ArrayList<>());
            for (int i = 0; i < serviceList.size(); i++){
                for (ServiceByProvider service: orders.getUserProvider().getServiceByProviderList()){
                    if (service.getName().equals(serviceList.get(i))){
                        orders.getServiceByProviderList().add(service);
                        count++;
                        money+=service.getPrice();
                    }
                }
            }
        }
        orders.setMoney(money * orderForm.getDuration());
        orders.setComment("Đã book "+count+" dịch vụ từ "+orderForm.getUserProvider().getName()+", Hãy chờ đối tác xác nhận");
        return new ResponseEntity<>(orderService.save(orders), HttpStatus.CREATED);
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
    @GetMapping("/user/{status}")//list order mình book người ta theo trạng thái
    public ResponseEntity<Iterable<Orders>> findAllByStatusAndUser(@PathVariable String status){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> currentUser = userService.findByUsername(username);
        if (!currentUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderService.findAllByUserAndStatusOrder(currentUser.get(), status), HttpStatus.OK);
    }
    @GetMapping("/provider/{status}")//list order người ta book mình theo trạng thái
    public ResponseEntity<Iterable<Orders>> findAllByStatusAndUserProvider(@PathVariable String status){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> currentUser = userService.findByUsername(username);
        if (!currentUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderService.findAllByUserProviderAndStatusOrder(currentUser.get(), status), HttpStatus.OK);
    }
    @GetMapping("/user-book")//list order mình book người ta
    public ResponseEntity<Iterable<Orders>> findAllByUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> currentUser = userService.findByUsername(username);
        if (!currentUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderService.findAllByUser(currentUser.get()), HttpStatus.OK);
    }
    @GetMapping("/book-provider")//list order người ta book mình
    public ResponseEntity<Iterable<Orders>> findAllByProvider(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> currentUser = userService.findByUsername(username);
        if (!currentUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderService.findAllByUserProvider(currentUser.get()), HttpStatus.OK);
    }
    @PutMapping("/received/{id}")
    public ResponseEntity<Orders> received(@PathVariable Long id){
        Optional<Orders> orders = orderService.findById(id);
        if (!orders.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (orders.get().getStatusOrder().equals("RECEIVED") || orders.get().getStatusOrder().equals("COMPLETE")){
            return new ResponseEntity<>(orders.get() ,HttpStatus.NO_CONTENT);
        }
        orders.get().setStatusOrder("RECEIVED");
        orders.get().setStartTime(LocalDate.now());
        orders.get().setComment("Đối tác đã xác nhận, chúc bạn có những giây phút thăng hoa");
        return new ResponseEntity<>(orderService.save(orders.get()), HttpStatus.ACCEPTED);
    }
    @PutMapping("/complete/{id}")
    public ResponseEntity<Orders> complete(@PathVariable Long id){
        Optional<Orders> orders = orderService.findById(id);
        if (!orders.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (orders.get().getStatusOrder().equals("COMPLETE") || orders.get().getStatusOrder().equals("PENDING")){
            return new ResponseEntity<>(orders.get() ,HttpStatus.NO_CONTENT);
        }
        orders.get().setStatusOrder("COMPLETE");
        orders.get().setComment("Cuộc vui đã tàn ngày: "+LocalDate.now());
        return new ResponseEntity<>(orderService.save(orders.get()), HttpStatus.ACCEPTED);
    }
}
