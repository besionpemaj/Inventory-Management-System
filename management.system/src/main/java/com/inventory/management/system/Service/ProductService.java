package com.inventory.management.system.Service;

import com.inventory.management.system.Dto.ProductRequestDto;
import com.inventory.management.system.Dto.ProductResponseDto;
import com.inventory.management.system.Entity.Category;
import com.inventory.management.system.Entity.Product;
import com.inventory.management.system.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public Product createProduct(ProductRequestDto requestDto) {

        Product product = modelMapper.map(requestDto, Product.class);

        return productRepository.save(product);
    }

    private boolean isValidSortField(String fieldName) {
        return Arrays.stream(Product.class.getDeclaredFields())
                .anyMatch(field -> field.getName().equals(fieldName));
    }

    public Page<ProductResponseDto> getAllProducts(int page, int size, String direction, String sortedBy, Long categoryId) {
        if (!this.isValidSortField(sortedBy)) {
            sortedBy = "createdAt";
        }
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortedBy).ascending() : Sort.by(sortedBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage;
        if (categoryId != null) {
            productPage = this.productRepository.findByCategory(new Category(categoryId), pageable);
        } else {
            productPage = this.productRepository.findAll(pageable);
        }

        Page<ProductResponseDto> dtoPage =
                productPage.map(product -> modelMapper.map(product,
                        ProductResponseDto.class));
        return dtoPage;
    }

    public Product updateProduct(Long id, ProductRequestDto requestDto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with ID: " + id));
        //ignore null values
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        //merge
        modelMapper.map(requestDto, existing);
        return this.productRepository.save(existing);
    }

    public void softDeleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with ID: " + id));
        product.setDeleted(true);
        productRepository.save(product);
    }
}
