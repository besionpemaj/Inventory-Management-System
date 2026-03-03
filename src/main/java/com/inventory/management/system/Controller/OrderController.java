package com.inventory.management.system.Controller;

import com.inventory.management.system.Dto.CategoryResponseDto;
import com.inventory.management.system.Dto.OrderRequestDto;
import com.inventory.management.system.Dto.OrderResponseDto;
import com.inventory.management.system.Dto.UpdateOrderDto;
import com.inventory.management.system.Entity.Order;
import com.inventory.management.system.Enums.OrderStatus;
import com.inventory.management.system.Service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponseDto> updateStatus(@PathVariable Integer id, @RequestBody UpdateOrderDto updateOrderDto){
        OrderResponseDto orderResponseDto = orderService.updateStatus(id,updateOrderDto.getOrderStatus());
        return new ResponseEntity<>(orderResponseDto,HttpStatus.OK);
    }

    @GetMapping
        public ResponseEntity<List<OrderResponseDto>> getAllOrders(){
        List<OrderResponseDto> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders,HttpStatus.CREATED);
    }
}
