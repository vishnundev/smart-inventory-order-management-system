package com.vishnu.inventory.controller;

import java.util.List;
import com.vishnu.inventory.dto.PurchaseOrderDto;
import com.vishnu.inventory.request.PlaceOrderRequest;

import jakarta.validation.Valid;

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
@PostMapping("/place")

public ResponseEntity<PurchaseOrderDto> placeOrder(

        @Valid

        @RequestBody

        PlaceOrderRequest request

) {

    PurchaseOrder order =

            purchaseOrderService.placeOrder(

                    request

            );

    return ResponseEntity.ok(

            purchaseOrderService.getById(

                    order.getId()

            )

    );

}

@GetMapping

public ResponseEntity<List<PurchaseOrderDto>> getAll() {

    return ResponseEntity.ok(

            purchaseOrderService.getAll()

    );

}

@GetMapping("/{id}")

public ResponseEntity<PurchaseOrderDto> getById(

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
