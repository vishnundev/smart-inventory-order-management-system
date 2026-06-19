package com.vishnu.inventory.service;

import java.util.List;
import com.vishnu.inventory.dto.PurchaseOrderDto;

import com.vishnu.inventory.entity.PurchaseOrder;

import com.vishnu.inventory.request.PlaceOrderRequest;

public interface PurchaseOrderService {

	List<PurchaseOrderDto> getAll();

	PurchaseOrderDto getById(Long id);

    PurchaseOrder placeOrder(

            PlaceOrderRequest request

    );

}