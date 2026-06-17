package com.vishnu.inventory.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class OrderItem {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Integer quantity;

private BigDecimal price;

@ManyToOne
@JoinColumn(name = "product_id")
private Product product;

@ManyToOne
@JoinColumn(name="purchase_order_id")
private PurchaseOrder purchaseOrder;


}
