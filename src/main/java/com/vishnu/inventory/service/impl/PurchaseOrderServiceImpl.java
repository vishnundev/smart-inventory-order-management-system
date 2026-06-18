package com.vishnu.inventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vishnu.inventory.entity.PurchaseOrder;
import com.vishnu.inventory.exception.ResourceNotFoundException;
import com.vishnu.inventory.repository.PurchaseOrderRepository;
import com.vishnu.inventory.service.PurchaseOrderService;

@Service

public class PurchaseOrderServiceImpl implements PurchaseOrderService {


private final PurchaseOrderRepository purchaseOrderRepository;

public PurchaseOrderServiceImpl(

        PurchaseOrderRepository purchaseOrderRepository

) {

    this.purchaseOrderRepository = purchaseOrderRepository;

}

@Override

public List<PurchaseOrder> getAll() {

    return purchaseOrderRepository.findAll();

}

@Override

public PurchaseOrder getById(

        Long id

) {

    return purchaseOrderRepository

            .findById(id)

            .orElseThrow(() ->

                    new ResourceNotFoundException(

                            "Purchase Order not found"

                    )

            );

}


}
