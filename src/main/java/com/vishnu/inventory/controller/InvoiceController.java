package com.vishnu.inventory.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.vishnu.inventory.dto.InvoiceDto;

import com.vishnu.inventory.service.InvoiceService;

@RestController

@RequestMapping("/api/invoices")

public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(

            InvoiceService invoiceService

    ) {

        this.invoiceService = invoiceService;

    }

    @GetMapping

    public ResponseEntity<List<InvoiceDto>> getAll() {

        return ResponseEntity.ok(

                invoiceService.getAll()

        );

    }

    @GetMapping("/{id}")

    public ResponseEntity<InvoiceDto> getById(

            @PathVariable

            Long id

    ) {

        return ResponseEntity.ok(

                invoiceService.getById(

                        id

                )

        );

    }

}