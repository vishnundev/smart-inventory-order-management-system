package com.vishnu.inventory.service;

import java.util.List;

import com.vishnu.inventory.entity.PurchaseOrder;

public interface PurchaseOrderService {


List<PurchaseOrder> getAll();

PurchaseOrder getById(

        Long id

);


}
