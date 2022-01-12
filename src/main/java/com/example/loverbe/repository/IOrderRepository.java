package com.example.loverbe.repository;

import com.example.loverbe.model.entity.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IOrderRepository extends JpaRepository<Orders, Long> {

}
