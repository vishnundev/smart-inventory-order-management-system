package com.vishnu.inventory.controller;

import java.util.List;
import com.vishnu.inventory.request.SupplierRequest;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.vishnu.inventory.dto.SupplierDto;
import com.vishnu.inventory.service.SupplierService;

@RestController

@RequestMapping("/api/suppliers")

public class SupplierController {


private final SupplierService supplierService;

public SupplierController(
        SupplierService supplierService
) {

    this.supplierService = supplierService;

}

@PostMapping

public ResponseEntity<SupplierDto> create(

        @Valid

        @RequestBody

        SupplierRequest request

) {

    return ResponseEntity.ok(

            supplierService.create(

                    request

            )

    );

}
@GetMapping

public ResponseEntity<List<SupplierDto>> getAll() {

    return ResponseEntity.ok(

            supplierService.getAll()

    );

}

@GetMapping("/{id}")

public ResponseEntity<SupplierDto> getById(

        @PathVariable

        Long id

) {

    return ResponseEntity.ok(

            supplierService.getById(
                    id
            )

    );

}


}
