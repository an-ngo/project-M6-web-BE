package com.example.loverbe.service;

import com.example.loverbe.model.entity.orders.Orders;
import com.example.loverbe.model.entity.user.User;

public interface IOrdersService extends IGeneralService<Orders>{
    Iterable<Orders> findAllByUserAndStatusOrder(User user, String statusOrder);
    Iterable<Orders> findAllByUserProviderAndStatusOrder(User userProvider, String statusOrder);
    Iterable<Orders> findAllByUser(User user);
    Iterable<Orders> findAllByUserProvider(User user);
}
