package com.example.loverbe.repository;

import com.example.loverbe.model.entity.orders.Orders;
import com.example.loverbe.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IOrdersRepository extends JpaRepository<Orders, Long> {
//    @Query(value = "select * from orders where user_provider_id = ? and enum_order = ? order by start_time;", nativeQuery = true)
//    Iterable<Orders> findAllStatus(Long id ,String status);
    Iterable<Orders> findAllByUserAndStatusOrder(User user, String statusOrder);
    Iterable<Orders> findAllByUserProviderAndStatusOrder(User userProvider, String statusOrder);
}
