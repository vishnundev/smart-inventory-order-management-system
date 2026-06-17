package com.vishnu.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishnu.inventory.entity.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

}
