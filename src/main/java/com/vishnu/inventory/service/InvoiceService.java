package com.vishnu.inventory.service;

import java.util.List;

import com.vishnu.inventory.dto.InvoiceDto;

public interface InvoiceService {

    List<InvoiceDto> getAll();

    InvoiceDto getById(

            Long id

    );

}