package com.vishnu.inventory.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor

@AllArgsConstructor

@Builder

public class InvoiceDto {

    private Long id;

    private String invoiceNumber;

    private LocalDateTime generatedAt;

    private Long orderId;

}