package com.example.loverbe.service.Implement;

import com.example.loverbe.model.entity.order.Orders;
import com.example.loverbe.repository.IOrderRepository;
import com.example.loverbe.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderService implements IOrderService {
    @Autowired
    IOrderRepository orderRepository;
    @Override
    public Iterable<Orders> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Orders save(Orders orders) {
        return orderRepository.save(orders);
    }

    @Override
    public void remove(Long id) {
orderRepository.deleteById(id);
    }
}
