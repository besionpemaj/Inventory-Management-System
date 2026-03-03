package com.inventory.management.system.Dto;

import com.inventory.management.system.Entity.Product;
import com.inventory.management.system.Enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponseDto {

    private int id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long customerId;
    private Product product;
    private int quantity;
    private double total;
    private OrderStatus orderStatus;
}