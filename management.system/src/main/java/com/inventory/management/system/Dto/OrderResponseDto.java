package com.inventory.management.system.Dto;

import com.inventory.management.system.Entity.Product;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponseDto {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long customerId;
    private Product product;
    private int quantity;
    private double total;
}