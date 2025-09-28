package com.inventory.management.system.Controller;

import com.inventory.management.system.Dto.ProductRequestDto;
import com.inventory.management.system.Dto.ProductResponseDto;
import com.inventory.management.system.Entity.Product;
import com.inventory.management.system.Repository.ProductRepository;
import com.inventory.management.system.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/product")
public class ProductController {


    private ProductService productService;
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping("createProduct")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Validated ProductRequestDto requestDto) {
        Product product = productService.createProduct(requestDto);
        ProductResponseDto responseDto = modelMapper.map(product, ProductResponseDto.class);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable("id") Long id){
        Optional<Product> product = productRepository.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
