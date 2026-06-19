package com.vishnu.inventory.service.impl;

import java.util.List;
import com.vishnu.inventory.entity.OrderStatus;
import com.vishnu.inventory.entity.Role;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vishnu.inventory.dto.PurchaseOrderDto;
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
import com.vishnu.inventory.mapper.EntityMapper;
import com.vishnu.inventory.repository.PurchaseOrderRepository;
import com.vishnu.inventory.service.PurchaseOrderService;

@Service

public class PurchaseOrderServiceImpl implements PurchaseOrderService {


private final PurchaseOrderRepository purchaseOrderRepository;
private final ProductRepository productRepository;

private final OrderItemRepository orderItemRepository;

private final InvoiceRepository invoiceRepository;

private final UserRepository userRepository;
private final EntityMapper entityMapper;

public PurchaseOrderServiceImpl(

        PurchaseOrderRepository purchaseOrderRepository,

        ProductRepository productRepository,

        OrderItemRepository orderItemRepository,

        InvoiceRepository invoiceRepository,

        UserRepository userRepository,
        EntityMapper entityMapper

) {

    this.purchaseOrderRepository = purchaseOrderRepository;

    this.productRepository = productRepository;

    this.orderItemRepository = orderItemRepository;

    this.invoiceRepository = invoiceRepository;

    this.userRepository = userRepository;
    this.entityMapper = entityMapper;

}

@Override

public List<PurchaseOrderDto> getAll() {

    return purchaseOrderRepository

            .findAll()

            .stream()

            .map(

                    entityMapper::toPurchaseOrderDto

            )

            .toList();

}

@Override

public PurchaseOrderDto getById(

        Long id

) {

    PurchaseOrder order =

            purchaseOrderRepository

            .findById(id)

            .orElseThrow(

                    () ->

                    new ResourceNotFoundException(

                            "Purchase Order not found"

                    )

            );

    return entityMapper.toPurchaseOrderDto(

            order

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

                            "Customer not found"

                    )

            );

    if (

            customer.getRole()

            != Role.CUSTOMER

    ) {

        throw new RuntimeException(

                "Only customers can place orders"

        );

    }

    PurchaseOrder purchaseOrder =

            PurchaseOrder.builder()

                    .orderDate(

                            LocalDateTime.now()

                    )

                    .status(

                            OrderStatus.PLACED

                    )

                    .user(

                            customer

                    )

                    .orderItems(

                            new ArrayList<>()

                    )

                    .totalAmount(

                            BigDecimal.ZERO

                    )

                    .build();

    BigDecimal total = BigDecimal.ZERO;

    for (

            OrderItemRequest item :

            request.getItems()

    ) {

        Product product =

                productRepository

                        .findById(

                                item.getProductId()

                        )

                        .orElseThrow(() ->

                                new ResourceNotFoundException(

                                        "Product not found"

                                )

                        );

        if (

                product.getStock()

                < item.getQuantity()

        ) {

            throw new RuntimeException(

                    "Insufficient stock for "

                    + product.getName()

            );

        }

        product.setStock(

                product.getStock()

                - item.getQuantity()

        );

        productRepository.save(

                product

        );

        BigDecimal itemPrice =

                product.getPrice()

                        .multiply(

                                BigDecimal.valueOf(

                                        item.getQuantity()

                                )

                        );

        total = total.add(

                itemPrice

        );

        OrderItem orderItem =

                OrderItem.builder()

                        .product(

                                product

                        )

                        .quantity(

                                item.getQuantity()

                        )

                        .price(

                                itemPrice

                        )

                        .purchaseOrder(

                                purchaseOrder

                        )

                        .build();

        purchaseOrder

                .getOrderItems()

                .add(

                        orderItem

                );

    }

    purchaseOrder.setTotalAmount(

            total

    );

    PurchaseOrder savedOrder =

            purchaseOrderRepository

                    .save(

                            purchaseOrder

                    );

    Invoice invoice =

            Invoice.builder()

                    .invoiceNumber(

                            "INV-"

                            + savedOrder.getId()

                    )

                    .generatedAt(

                            LocalDateTime.now()

                    )

                    .purchaseOrder(

                            savedOrder

                    )

                    .build();

    invoiceRepository.save(

            invoice

    );

    return savedOrder;

}


}
