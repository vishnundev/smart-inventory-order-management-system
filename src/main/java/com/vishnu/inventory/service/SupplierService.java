package com.vishnu.inventory.service;

import java.util.List;
import com.vishnu.inventory.request.SupplierRequest;

import com.vishnu.inventory.dto.SupplierDto;

public interface SupplierService {

    List<SupplierDto> getAll();

    SupplierDto getById(Long id);
    SupplierDto create(

            SupplierRequest request

    );

}