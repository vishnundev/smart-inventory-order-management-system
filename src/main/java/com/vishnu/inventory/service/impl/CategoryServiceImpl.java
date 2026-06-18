package com.vishnu.inventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vishnu.inventory.dto.CategoryDto;
import com.vishnu.inventory.entity.Category;
import com.vishnu.inventory.exception.ResourceNotFoundException;
import com.vishnu.inventory.mapper.EntityMapper;
import com.vishnu.inventory.repository.CategoryRepository;
import com.vishnu.inventory.request.CategoryRequest;
import com.vishnu.inventory.service.CategoryService;

@Service

public class CategoryServiceImpl implements CategoryService {


private final CategoryRepository categoryRepository;

private final EntityMapper entityMapper;

public CategoryServiceImpl(

        CategoryRepository categoryRepository,

        EntityMapper entityMapper

) {

    this.categoryRepository = categoryRepository;

    this.entityMapper = entityMapper;

}

@Override

public CategoryDto create(

        CategoryRequest request

) {

    Category category = Category.builder()

            .name(

                    request.getName()

            )

            .description(

                    request.getDescription()

            )

            .build();

    categoryRepository.save(

            category

    );

    return entityMapper.toCategoryDto(

            category

    );

}

@Override

public List<CategoryDto> getAll() {

    return categoryRepository

            .findAll()

            .stream()

            .map(entityMapper::toCategoryDto)

            .toList();

}

@Override

public CategoryDto getById(

        Long id

) {

    Category category = categoryRepository

            .findById(id)

            .orElseThrow(() ->

                    new ResourceNotFoundException(

                            "Category not found"

                    )

            );

    return entityMapper.toCategoryDto(

            category

    );

}

@Override

public CategoryDto update(

        Long id,

        CategoryRequest request

) {

    Category category = categoryRepository

            .findById(id)

            .orElseThrow(() ->

                    new ResourceNotFoundException(

                            "Category not found"

                    )

            );

    category.setName(

            request.getName()

    );

    category.setDescription(

            request.getDescription()

    );

    categoryRepository.save(

            category

    );

    return entityMapper.toCategoryDto(

            category

    );

}

@Override

public void delete(

        Long id

) {

    categoryRepository.deleteById(

            id

    );

}


}
