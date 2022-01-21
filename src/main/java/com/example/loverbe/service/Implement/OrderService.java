package com.example.loverbe.service.Implement;

import com.example.loverbe.enums.EnumOrder;
import com.example.loverbe.model.entity.orders.Orders;
import com.example.loverbe.model.entity.user.User;
import com.example.loverbe.repository.IOrdersRepository;
import com.example.loverbe.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderService implements IOrdersService {
    @Autowired
    private IOrdersRepository orderRepository;
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

    @Override
    public Iterable<Orders> findAllByUserAndStatusOrder(User user, EnumOrder statusOrder) {
        return orderRepository.findAllByUserAndStatusOrder(user, statusOrder);
    }

    @Override
    public Iterable<Orders> findAllByUserProviderAndStatusOrder(User userProvider, EnumOrder statusOrder) {
        return orderRepository.findAllByUserProviderAndStatusOrder(userProvider, statusOrder);
    }

    @Override
    public Iterable<Orders> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    @Override
    public Iterable<Orders> findAllByUserProvider(User user) {
        return orderRepository.findAllByUserProvider(user);
    }
}
