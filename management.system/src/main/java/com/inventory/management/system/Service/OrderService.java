package com.inventory.management.system.Service;

import com.inventory.management.system.Dto.OrderRequestDto;
import com.inventory.management.system.Dto.OrderResponseDto;
import com.inventory.management.system.Entity.Order;
import com.inventory.management.system.Entity.Product;
import com.inventory.management.system.Repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;

    private ModelMapper modelMapper = new ModelMapper();
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
}
