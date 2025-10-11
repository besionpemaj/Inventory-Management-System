package com.inventory.management.system.Entity;

import com.inventory.management.system.Enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private int customerId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    private int quantity;

    private double total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.NEW;

}
