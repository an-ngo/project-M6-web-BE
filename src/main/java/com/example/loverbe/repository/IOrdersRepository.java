package com.example.loverbe.repository;

import com.example.loverbe.enums.EnumOrder;
import com.example.loverbe.model.entity.orders.Orders;
import com.example.loverbe.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IOrdersRepository extends JpaRepository<Orders, Long> {
    Iterable<Orders> findAllByUserAndStatusOrder(User user, EnumOrder statusOrder);
    Iterable<Orders> findAllByUserProviderAndStatusOrder(User userProvider, EnumOrder statusOrder);
    Iterable<Orders> findAllByUser(User user);
    Iterable<Orders> findAllByUserProvider(User user);
}
