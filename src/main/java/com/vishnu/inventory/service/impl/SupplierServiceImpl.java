package com.vishnu.inventory.service.impl;

import java.util.List;
import com.vishnu.inventory.request.SupplierRequest;

import org.springframework.stereotype.Service;

import com.vishnu.inventory.dto.SupplierDto;
import com.vishnu.inventory.entity.Supplier;
import com.vishnu.inventory.exception.ResourceNotFoundException;
import com.vishnu.inventory.mapper.EntityMapper;
import com.vishnu.inventory.repository.SupplierRepository;
import com.vishnu.inventory.service.SupplierService;

@Service

public class SupplierServiceImpl implements SupplierService {

private final SupplierRepository supplierRepository;

private final EntityMapper entityMapper;

public SupplierServiceImpl(

        SupplierRepository supplierRepository,

        EntityMapper entityMapper

) {

    this.supplierRepository = supplierRepository;

    this.entityMapper = entityMapper;

}

@Override

public List<SupplierDto> getAll() {

    return supplierRepository

            .findAll()

            .stream()

            .map(entityMapper::toSupplierDto)

            .toList();

}

@Override

public SupplierDto getById(

        Long id

) {

    Supplier supplier = supplierRepository

            .findById(id)

            .orElseThrow(() ->

                    new ResourceNotFoundException(

                            "Supplier not found"

                    )

            );

    return entityMapper.toSupplierDto(

            supplier

    );

}
@Override

public SupplierDto create(

        SupplierRequest request

) {

    Supplier supplier =

            Supplier.builder()

                    .name(

                            request.getName()

                    )

                    .email(

                            request.getEmail()

                    )

                    .phone(

                            request.getPhone()

                    )

                    .address(

                            request.getAddress()

                    )

                    .build();

    supplier = supplierRepository.save(

            supplier

    );

    return entityMapper.toSupplierDto(

            supplier

    );

}

}
