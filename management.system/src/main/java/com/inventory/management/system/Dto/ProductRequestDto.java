package com.inventory.management.system.Dto;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Long category;
}
