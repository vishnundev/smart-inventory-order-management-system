package com.vishnu.inventory.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.vishnu.inventory.dto.CategoryDto;
import com.vishnu.inventory.request.CategoryRequest;
import com.vishnu.inventory.service.CategoryService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/api/categories")

public class CategoryController {


private final CategoryService categoryService;

public CategoryController(
        CategoryService categoryService
) {

    this.categoryService = categoryService;

}

@PostMapping

public ResponseEntity<CategoryDto> create(

        @Valid

        @RequestBody

        CategoryRequest request

) {

    return ResponseEntity.ok(

            categoryService.create(
                    request
            )

    );

}

@GetMapping

public ResponseEntity<List<CategoryDto>> getAll() {

    return ResponseEntity.ok(

            categoryService.getAll()

    );

}

@GetMapping("/{id}")

public ResponseEntity<CategoryDto> getById(

        @PathVariable

        Long id

) {

    return ResponseEntity.ok(

            categoryService.getById(
                    id
            )

    );

}

@PutMapping("/{id}")

public ResponseEntity<CategoryDto> update(

        @PathVariable

        Long id,

        @RequestBody

        CategoryRequest request

) {

    return ResponseEntity.ok(

            categoryService.update(
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

    categoryService.delete(
            id
    );

    return ResponseEntity.ok(

            "Category deleted"

    );

}


}
