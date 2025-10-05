package com.inventory.management.system.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private String description;
}
