package com.yc.SecurePro.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.SecurePro.dto.req.CategoryRequestDto;
import com.yc.SecurePro.dto.res.CategoryResponseDto;
import com.yc.SecurePro.exception.ResourceNotFoundException;
import com.yc.SecurePro.service.Interfaces.ICategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private ICategoryService categoryService;


    public CategoryController(ICategoryService categoryService){
        this.categoryService=categoryService;
    }

    @GetMapping("/user/categories")
    public Page<CategoryResponseDto> getAllCategories(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(value = "sort", defaultValue = "name") String sortField,
        @RequestParam(value = "direction", defaultValue = "asc") String direction) {
    
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));
        
        return categoryService.allCategories(pageable);
    }

    @GetMapping("/categories/search")
    public ResponseEntity<?> getCategoriesByName(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "name") String sortField,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        try {
            Sort.Direction sortDirection = Sort.Direction.fromString(direction);
            
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));
            
            Page<CategoryResponseDto> categories = categoryService.getCategoriesByName(name, pageable);

            return ResponseEntity.ok(categories);
            
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }


   

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/categories")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryRequestDto categoryDto) {
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

     @PreAuthorize("hasRole('ADMIN')")
     @PutMapping("/admin/categories/{categoryId}")
     public ResponseEntity<?> updateCategory(
             @PathVariable Long categoryId,
             @RequestBody CategoryRequestDto categoryDto) {
        try {

            CategoryResponseDto updatedCategory = categoryService.updateCategory(categoryId, categoryDto);
            return ResponseEntity.ok(updatedCategory);

        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok("Category deleted successfully");

        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());

        } catch (Exception ex) {
            // Catch any other unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred");
        }
    }

    // // Get current authenticated user details
   
}
