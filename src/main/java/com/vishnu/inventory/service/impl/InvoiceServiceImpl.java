package com.vishnu.inventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vishnu.inventory.dto.InvoiceDto;

import com.vishnu.inventory.entity.Invoice;

import com.vishnu.inventory.exception.ResourceNotFoundException;

import com.vishnu.inventory.mapper.EntityMapper;

import com.vishnu.inventory.repository.InvoiceRepository;

import com.vishnu.inventory.service.InvoiceService;

@Service

public class InvoiceServiceImpl

implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final EntityMapper entityMapper;

    public InvoiceServiceImpl(

            InvoiceRepository invoiceRepository,

            EntityMapper entityMapper

    ) {

        this.invoiceRepository = invoiceRepository;

        this.entityMapper = entityMapper;

    }

    @Override

    public List<InvoiceDto> getAll() {

        return invoiceRepository

                .findAll()

                .stream()

                .map(

                        entityMapper::toInvoiceDto

                )

                .toList();

    }

    @Override

    public InvoiceDto getById(

            Long id

    ) {

        Invoice invoice =

                invoiceRepository

                .findById(id)

                .orElseThrow(

                        () ->

                        new ResourceNotFoundException(

                                "Invoice not found"

                        )

                );

        return entityMapper.toInvoiceDto(

                invoice

        );

    }

}