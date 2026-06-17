package com.vishnu.inventory.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchase_orders")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PurchaseOrder {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private LocalDateTime orderDate;

private BigDecimal totalAmount;

private String status;

@ManyToOne
@JoinColumn(name = "user_id")
private User user;

@OneToMany(
        mappedBy = "purchaseOrder",
        cascade = CascadeType.ALL,
        orphanRemoval = true
)
private List<OrderItem> orderItems;


}
