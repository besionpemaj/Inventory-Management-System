package com.inventory.management.system.Dto;

import com.inventory.management.system.Entity.Category;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProductResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private Category category;
}
