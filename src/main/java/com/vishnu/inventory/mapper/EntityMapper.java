package com.vishnu.inventory.mapper;

import org.modelmapper.ModelMapper;
import com.vishnu.inventory.dto.InvoiceDto;

import com.vishnu.inventory.entity.Invoice;

import org.springframework.stereotype.Component;

import com.vishnu.inventory.dto.CategoryDto;
import com.vishnu.inventory.dto.ProductDto;
import com.vishnu.inventory.dto.SupplierDto;
import com.vishnu.inventory.dto.UserDto;

import com.vishnu.inventory.entity.Category;
import com.vishnu.inventory.entity.Product;
import com.vishnu.inventory.entity.Supplier;
import com.vishnu.inventory.entity.User;
import com.vishnu.inventory.dto.PurchaseOrderDto;

import com.vishnu.inventory.entity.PurchaseOrder;

@Component

public class EntityMapper {


private final ModelMapper mapper;

public EntityMapper(
        ModelMapper mapper
) {

    this.mapper = mapper;

}

public UserDto toUserDto(
        User user
) {

    return mapper.map(
            user,
            UserDto.class
    );

}

public CategoryDto toCategoryDto(
        Category category
) {

    return mapper.map(
            category,
            CategoryDto.class
    );

}

public SupplierDto toSupplierDto(
        Supplier supplier
) {

    return mapper.map(
            supplier,
            SupplierDto.class
    );

}
public Supplier toSupplierEntity(

        SupplierDto supplierDto

) {

    return mapper.map(

            supplierDto,

            Supplier.class

    );

}

public ProductDto toProductDto(
        Product product
) {

    ProductDto dto = mapper.map(
            product,
            ProductDto.class
    );

    if(product.getCategory()!=null){

        dto.setCategoryId(
                product
                        .getCategory()
                        .getId()
        );

    }

    if(product.getSupplier()!=null){

        dto.setSupplierId(
                product
                        .getSupplier()
                        .getId()
        );

    }

    return dto;

}
public InvoiceDto toInvoiceDto(

        Invoice invoice

) {

    InvoiceDto dto =

            mapper.map(

                    invoice,

                    InvoiceDto.class

            );

    if(

            invoice.getPurchaseOrder()

            != null

    ) {

        dto.setOrderId(

                invoice

                        .getPurchaseOrder()

                        .getId()

        );

    }

    return dto;

}
public PurchaseOrderDto toPurchaseOrderDto(

        PurchaseOrder order

) {

    PurchaseOrderDto dto =

            mapper.map(

                    order,

                    PurchaseOrderDto.class

            );

    if(

            order.getUser()

            != null

    ) {

        dto.setUserId(

                order

                        .getUser()

                        .getId()

        );

    }

    return dto;

}


}
