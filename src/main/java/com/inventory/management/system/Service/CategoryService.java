package com.inventory.management.system.Service;

import com.inventory.management.system.Dto.CategoryRequestDto;
import com.inventory.management.system.Dto.CategoryResponseDto;
import com.inventory.management.system.Entity.Category;
import com.inventory.management.system.Repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.asm.IModelFilter;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public Category createProduct(CategoryRequestDto requestDto) {
        Category category = modelMapper.map(requestDto,Category.class);
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategoryByName(String name){
        return categoryRepository.findByName(name);
    };

    public Category update(Long id, CategoryRequestDto requestDto) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id:" + id));
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(requestDto,existing);
        return this.categoryRepository.save(existing);
    }

    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDto> responseDtos = new ArrayList<>();
        for(Category category:categories){
            CategoryResponseDto dto = modelMapper.map(category,CategoryResponseDto.class);
            responseDtos.add(dto);
        }
        return responseDtos;
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id:" + id));
        categoryRepository.delete(category);
    }
}
