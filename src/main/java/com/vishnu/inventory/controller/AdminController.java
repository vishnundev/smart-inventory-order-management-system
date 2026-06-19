package com.vishnu.inventory.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vishnu.inventory.repository.*;

@RestController

@RequestMapping("/api/admin")

public class AdminController {

    private final UserRepository userRepository;

    private final SupplierRepository supplierRepository;

    private final ProductRepository productRepository;

    private final PurchaseOrderRepository purchaseOrderRepository;

    public AdminController(

            UserRepository userRepository,

            SupplierRepository supplierRepository,

            ProductRepository productRepository,

            PurchaseOrderRepository purchaseOrderRepository

    ) {

        this.userRepository = userRepository;

        this.supplierRepository = supplierRepository;

        this.productRepository = productRepository;

        this.purchaseOrderRepository = purchaseOrderRepository;

    }

    @GetMapping("/dashboard")

    public ResponseEntity<Map<String,Object>> dashboard() {

        Map<String,Object> data =

                new HashMap<>();

        data.put(

                "users",

                userRepository.count()

        );

        data.put(

                "suppliers",

                supplierRepository.count()

        );

        data.put(

                "products",

                productRepository.count()

        );

        data.put(

                "orders",

                purchaseOrderRepository.count()

        );

        return ResponseEntity.ok(

                data

        );

    }

}