package com.inventory.management.system.Controller;

import com.inventory.management.system.Dto.ProductRequestDto;
import com.inventory.management.system.Dto.ProductResponseDto;
import com.inventory.management.system.Entity.Product;
import com.inventory.management.system.Repository.ProductRepository;
import com.inventory.management.system.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Autowired
    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Validated ProductRequestDto requestDto) {
        Product product = productService.createProduct(requestDto);
        ProductResponseDto responseDto = modelMapper.map(product, ProductResponseDto.class);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable("id") Long id) {
        Optional<Product> product = productRepository.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<Optional<Product>> getProductByName(@PathVariable("name") String name) {
        Optional<Product> product = productRepository.findByName(name);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getAllProducts(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "20", required = false) int size,
            @RequestParam(name = "direction", defaultValue = "desc", required = false) String direction,
            @RequestParam(name = "sort_by", defaultValue = "createdAt", required = false) String sortedBy,
            @RequestParam(name = "category_id", required = false) Long categoryId) {

        return new ResponseEntity<>(productService.getAllProducts(page, size, direction, sortedBy, categoryId), HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ProductRequestDto requestDto, @PathVariable("id") Long id){
        Product product = productService.updateProduct(id, requestDto);
        ProductResponseDto responseDto = modelMapper.map(product, ProductResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
        productService.softDeleteProduct(id);
        return ResponseEntity.ok("Deleted the product.");
    }
}
