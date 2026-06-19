package com.vishnu.inventory.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor

@AllArgsConstructor

@Builder

public class PurchaseOrderDto {

    private Long id;

    private LocalDateTime orderDate;

    private BigDecimal totalAmount;

    private String status;

    private Long userId;

}