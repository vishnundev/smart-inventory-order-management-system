package com.vishnu.inventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vishnu.inventory.dto.ProductDto;
import com.vishnu.inventory.entity.Category;
import com.vishnu.inventory.entity.Product;
import com.vishnu.inventory.entity.Supplier;
import com.vishnu.inventory.exception.ResourceNotFoundException;
import com.vishnu.inventory.mapper.EntityMapper;
import com.vishnu.inventory.repository.CategoryRepository;
import com.vishnu.inventory.repository.ProductRepository;
import com.vishnu.inventory.repository.SupplierRepository;
import com.vishnu.inventory.request.ProductRequest;
import com.vishnu.inventory.service.ProductService;

@Service

public class ProductServiceImpl implements ProductService {

private final ProductRepository productRepository;

private final CategoryRepository categoryRepository;

private final SupplierRepository supplierRepository;

private final EntityMapper entityMapper;

public ProductServiceImpl(

        ProductRepository productRepository,

        CategoryRepository categoryRepository,

        SupplierRepository supplierRepository,

        EntityMapper entityMapper

) {

    this.productRepository = productRepository;

    this.categoryRepository = categoryRepository;

    this.supplierRepository = supplierRepository;

    this.entityMapper = entityMapper;

}

@Override

public ProductDto create(

        ProductRequest request

) {

    Category category = categoryRepository

            .findById(

                    request.getCategoryId()

            )

            .orElseThrow(() ->

                    new ResourceNotFoundException(

                            "Category not found"

                    )

            );

    Supplier supplier = supplierRepository

            .findById(

                    request.getSupplierId()

            )

            .orElseThrow(() ->

                    new ResourceNotFoundException(

                            "Supplier not found"

                    )

            );

    Product product = Product.builder()

            .name(

                    request.getName()

            )

            .description(

                    request.getDescription()

            )

            .price(

                    request.getPrice()

            )

            .stock(

                    request.getStock()

            )

            .category(

                    category

            )

            .supplier(

                    supplier

            )

            .build();

    productRepository.save(

            product

    );

    return entityMapper.toProductDto(

            product

    );

}

@Override

public List<ProductDto> getAll() {

    return productRepository

            .findAll()

            .stream()

            .map(entityMapper::toProductDto)

            .toList();

}

@Override

public ProductDto getById(

        Long id

) {

    Product product = productRepository

            .findById(id)

            .orElseThrow(() ->

                    new ResourceNotFoundException(

                            "Product not found"

                    )

            );

    return entityMapper.toProductDto(

            product

    );

}

@Override

public List<ProductDto> search(

        String name

) {

    return productRepository

            .findByNameContainingIgnoreCase(

                    name

            )

            .stream()

            .map(entityMapper::toProductDto)

            .toList();

}

@Override

public ProductDto update(

        Long id,

        ProductRequest request

) {

    Product product = productRepository

            .findById(id)

            .orElseThrow(() ->

                    new ResourceNotFoundException(

                            "Product not found"

                    )

            );

    Category category = categoryRepository

            .findById(

                    request.getCategoryId()

            )

            .orElseThrow(() ->

                    new ResourceNotFoundException(

                            "Category not found"

                    )

            );

    Supplier supplier = supplierRepository

            .findById(

                    request.getSupplierId()

            )

            .orElseThrow(() ->

                    new ResourceNotFoundException(

                            "Supplier not found"

                    )

            );

    product.setName(

            request.getName()

    );

    product.setDescription(

            request.getDescription()

    );

    product.setPrice(

            request.getPrice()

    );

    product.setStock(

            request.getStock()

    );

    product.setCategory(

            category

    );

    product.setSupplier(

            supplier

    );

    productRepository.save(

            product

    );

    return entityMapper.toProductDto(

            product

    );

}

@Override

public void delete(

        Long id

) {

    productRepository.deleteById(

            id

    );

}


}
