package com.vishnu.inventory.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.vishnu.inventory.dto.ProductDto;
import com.vishnu.inventory.request.ProductRequest;
import com.vishnu.inventory.service.ProductService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/api/products")

public class ProductController {


private final ProductService productService;

public ProductController(
        ProductService productService
) {

    this.productService = productService;

}

@PostMapping

public ResponseEntity<ProductDto> create(

        @Valid

        @RequestBody

        ProductRequest request

) {

    return ResponseEntity.ok(

            productService.create(
                    request
            )

    );

}

@GetMapping

public ResponseEntity<List<ProductDto>> getAll() {

    return ResponseEntity.ok(

            productService.getAll()

    );

}

@GetMapping("/{id}")

public ResponseEntity<ProductDto> getById(

        @PathVariable

        Long id

) {

    return ResponseEntity.ok(

            productService.getById(
                    id
            )

    );

}

@GetMapping("/search")

public ResponseEntity<List<ProductDto>> search(

        @RequestParam

        String name

) {

    return ResponseEntity.ok(

            productService.search(
                    name
            )

    );

}

@PutMapping("/{id}")

public ResponseEntity<ProductDto> update(

        @PathVariable

        Long id,

        @RequestBody

        ProductRequest request

) {

    return ResponseEntity.ok(

            productService.update(
                    id,
                    request
            )

    );

}

@DeleteMapping("/{id}")

public ResponseEntity<String> delete(

        @PathVariable

        Long id

) {

    productService.delete(
            id
    );

    return ResponseEntity.ok(

            "Product deleted"

    );

}


}
