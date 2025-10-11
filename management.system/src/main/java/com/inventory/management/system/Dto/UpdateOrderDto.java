package com.inventory.management.system.Dto;

import com.inventory.management.system.Enums.OrderStatus;

public class UpdateOrderDto {
    private OrderStatus orderStatus;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
