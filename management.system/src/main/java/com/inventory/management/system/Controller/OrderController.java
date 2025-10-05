package com.inventory.management.system.Controller;

import com.inventory.management.system.Dto.OrderRequestDto;
import com.inventory.management.system.Dto.OrderResponseDto;
import com.inventory.management.system.Entity.Order;
import com.inventory.management.system.Service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@RequestBody @Validated OrderRequestDto requestDto){
        OrderResponseDto order = orderService.createOrder(requestDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
