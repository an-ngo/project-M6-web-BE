package com.example.loverbe.repository.order;

import com.example.loverbe.model.entity.order.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends PagingAndSortingRepository<Order,Long> {
}
