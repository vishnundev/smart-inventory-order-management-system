package com.vishnu.inventory.service.impl;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vishnu.inventory.entity.Invoice;
import com.vishnu.inventory.entity.OrderItem;
import com.vishnu.inventory.entity.Product;
import com.vishnu.inventory.entity.User;

import com.vishnu.inventory.request.PlaceOrderRequest;
import com.vishnu.inventory.request.OrderItemRequest;

import com.vishnu.inventory.repository.InvoiceRepository;
import com.vishnu.inventory.repository.OrderItemRepository;
import com.vishnu.inventory.repository.ProductRepository;
import com.vishnu.inventory.repository.UserRepository;

import org.springframework.stereotype.Service;

import com.vishnu.inventory.entity.PurchaseOrder;
import com.vishnu.inventory.exception.ResourceNotFoundException;
import com.vishnu.inventory.repository.PurchaseOrderRepository;
import com.vishnu.inventory.service.PurchaseOrderService;

@Service

public class PurchaseOrderServiceImpl implements PurchaseOrderService {


private final PurchaseOrderRepository purchaseOrderRepository;
private final ProductRepository productRepository;

private final OrderItemRepository orderItemRepository;

private final InvoiceRepository invoiceRepository;

private final UserRepository userRepository;

public PurchaseOrderServiceImpl(

        PurchaseOrderRepository purchaseOrderRepository,

        ProductRepository productRepository,

        OrderItemRepository orderItemRepository,

        InvoiceRepository invoiceRepository,

        UserRepository userRepository

) {

    this.purchaseOrderRepository = purchaseOrderRepository;

    this.productRepository = productRepository;

    this.orderItemRepository = orderItemRepository;

    this.invoiceRepository = invoiceRepository;

    this.userRepository = userRepository;

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
@Override

public PurchaseOrder placeOrder(

        PlaceOrderRequest request

) {

    Authentication authentication =

            SecurityContextHolder

                    .getContext()

                    .getAuthentication();

    String email = authentication.getName();

    User customer = userRepository

            .findByEmail(email)

            .orElseThrow(() ->

                    new ResourceNotFoundException(

                            "User not found"

                    )

            );

    if (

            customer.getRole()

            != com.vishnu.inventory.entity.Role.CUSTOMER

    ) {

        throw new RuntimeException(

                "Only customers can place orders"

        );

    }

    return null;

}


}
