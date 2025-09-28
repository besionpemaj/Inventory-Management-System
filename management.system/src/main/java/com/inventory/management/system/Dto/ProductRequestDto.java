package com.inventory.management.system.Dto;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private String description;
    private double price;
    private int quantity;
    private long category;


}
