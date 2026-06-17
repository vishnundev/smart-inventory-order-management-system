package com.vishnu.inventory.service;

import java.util.List;

import com.vishnu.inventory.dto.CategoryDto;

import com.vishnu.inventory.request.CategoryRequest;

public interface CategoryService {


CategoryDto create(

        CategoryRequest request

);

List<CategoryDto> getAll();

CategoryDto getById(

        Long id

);

CategoryDto update(

        Long id,

        CategoryRequest request

);

void delete(

        Long id

);


}
