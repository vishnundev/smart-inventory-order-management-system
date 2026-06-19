package com.vishnu.inventory.service;

import java.util.List;

import com.vishnu.inventory.entity.PurchaseOrder;

import com.vishnu.inventory.request.PlaceOrderRequest;

public interface PurchaseOrderService {

    List<PurchaseOrder> getAll();

    PurchaseOrder getById(

            Long id

    );

    PurchaseOrder placeOrder(

            PlaceOrderRequest request

    );

}