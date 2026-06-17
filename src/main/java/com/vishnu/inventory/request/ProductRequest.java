package com.vishnu.inventory.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data

public class ProductRequest {


@NotNull
private String name;

private String description;

@NotNull
private BigDecimal price;

@NotNull
private Integer stock;

@NotNull
private Long categoryId;

@NotNull
private Long supplierId;


}
