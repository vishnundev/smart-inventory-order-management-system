package com.vishnu.inventory.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoices")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Invoice {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String invoiceNumber;

private LocalDateTime generatedAt;

@OneToOne
@JoinColumn(name = "order_id")
private PurchaseOrder purchaseOrder;


}
