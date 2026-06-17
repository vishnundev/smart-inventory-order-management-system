package com.vishnu.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishnu.inventory.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
