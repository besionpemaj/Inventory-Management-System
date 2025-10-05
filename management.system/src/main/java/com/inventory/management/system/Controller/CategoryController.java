package com.inventory.management.system.Controller;

import com.inventory.management.system.Dto.CategoryRequestDto;
import com.inventory.management.system.Dto.CategoryResponseDto;
import com.inventory.management.system.Entity.Category;
import com.inventory.management.system.Service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody @Validated CategoryRequestDto requestDto){
        Category category = categoryService.createProduct(requestDto);
        CategoryResponseDto responseDto = modelMapper.map(category,CategoryResponseDto.class);
        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }
    @GetMapping("/{name}")
    public ResponseEntity<Optional<Category>> getCategoryByName(@PathVariable("name") String name){
        return new ResponseEntity<>(categoryService.getCategoryByName(name), HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@RequestBody CategoryRequestDto requestDto,@PathVariable("id") Long id){
        Category category = categoryService.update(id,requestDto);
        CategoryResponseDto responseDto = modelMapper.map(category,CategoryResponseDto.class);
        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(){
        List<CategoryResponseDto> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Deleted the product");
    }

}
