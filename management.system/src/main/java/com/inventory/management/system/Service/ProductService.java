package com.inventory.management.system.Service;

import com.inventory.management.system.Dto.ProductRequestDto;
import com.inventory.management.system.Entity.Product;
import com.inventory.management.system.Repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private ModelMapper modelMapper = new ModelMapper();
    public Product createProduct(ProductRequestDto requestDto) {

        Product product = modelMapper.map(requestDto,Product.class);

        return productRepository.save(product);
    }
}
