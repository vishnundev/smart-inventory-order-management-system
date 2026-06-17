package com.vishnu.inventory.service;

import java.util.List;

import com.vishnu.inventory.dto.ProductDto;

import com.vishnu.inventory.request.ProductRequest;

public interface ProductService {


ProductDto create(

        ProductRequest request

);

List<ProductDto> getAll();

ProductDto getById(

        Long id

);

List<ProductDto> search(

        String name

);

ProductDto update(

        Long id,

        ProductRequest request

);

void delete(

        Long id

);


}
