package com.inventory.management.system.Dto;

import com.inventory.management.system.Entity.Product;
import lombok.Data;

@Data
public class OrderRequestDto {
    private Long customerId;
    private Long productId;
    private int quantity;

}
