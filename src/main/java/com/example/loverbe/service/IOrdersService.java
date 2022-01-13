package com.example.loverbe.service;

import com.example.loverbe.model.entity.orders.Orders;
import com.example.loverbe.model.entity.user.User;

public interface IOrdersService extends IGeneralService<Orders>{
//    Iterable<Orders> findAllStatus(Long id ,String status);
    Iterable<Orders> findAllByUserAndStatusOrder(User user, String statusOrder);
    Iterable<Orders> findAllByUserProviderAndStatusOrder(User userProvider, String statusOrder);
}
