package com.inventory.management.system.Service;

import com.inventory.management.system.Dto.CategoryResponseDto;
import com.inventory.management.system.Dto.OrderRequestDto;
import com.inventory.management.system.Dto.OrderResponseDto;
import com.inventory.management.system.Entity.Category;
import com.inventory.management.system.Entity.Order;
import com.inventory.management.system.Entity.Product;
import com.inventory.management.system.Enums.OrderStatus;
import com.inventory.management.system.Repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;

    private ModelMapper modelMapper = new ModelMapper();

    public Order getOrderById(int id){
        Optional<Order> order = orderRepository.findById(id);
        if(!order.isPresent()){
            throw new EntityNotFoundException("No product with this given id");
        }
        return order.get();
    }

    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        Order order = new Order();
        Product product = this.productService.getProductById(requestDto.getProductId());
        order.setCustomerId(requestDto.getCustomerId());
        order.setQuantity(requestDto.getQuantity());
        order.setProduct(product);
        order.setTotal(product.getPrice() * requestDto.getQuantity());
        orderRepository.save(order);
        return modelMapper.map(order,OrderResponseDto.class);
    }

    public OrderResponseDto updateStatus(int id, OrderStatus status) {
        Order order = this.getOrderById(id);
        order.setStatus(status);
        orderRepository.save(order);
        return modelMapper.map(order,OrderResponseDto.class);
    }

    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDto> responseDtos = new ArrayList<>();
        for(Order order:orders){
            OrderResponseDto dto = modelMapper.map(order,OrderResponseDto.class);
            responseDtos.add(dto);
        }
        return responseDtos;
    }
}
