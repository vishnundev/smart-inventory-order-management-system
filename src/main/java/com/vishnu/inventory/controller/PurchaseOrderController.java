package com.vishnu.inventory.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.vishnu.inventory.entity.PurchaseOrder;
import com.vishnu.inventory.service.PurchaseOrderService;

@RestController

@RequestMapping("/api/orders")

public class PurchaseOrderController {


private final PurchaseOrderService purchaseOrderService;

public PurchaseOrderController(
        PurchaseOrderService purchaseOrderService
) {

    this.purchaseOrderService = purchaseOrderService;

}

@GetMapping

public ResponseEntity<List<PurchaseOrder>> getAll() {

    return ResponseEntity.ok(

            purchaseOrderService.getAll()

    );

}

@GetMapping("/{id}")

public ResponseEntity<PurchaseOrder> getById(

        @PathVariable

        Long id

) {

    return ResponseEntity.ok(

            purchaseOrderService.getById(
                    id
            )

    );

}


}
