package com.yc.SecurePro.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yc.SecurePro.dto.req.CategoryRequestDto;
import com.yc.SecurePro.dto.res.CategoryResponseDto;
import com.yc.SecurePro.entity.Category;
import com.yc.SecurePro.exception.ResourceNotFoundException;
import com.yc.SecurePro.mapper.CategoryMapper;
import com.yc.SecurePro.repository.CategoryRepository;
import com.yc.SecurePro.service.Interfaces.ICategoryService;
import com.yc.SecurePro.validator.CategoryValidator;

import jakarta.validation.Valid;


@Service
public class CategoryServiceImpl implements ICategoryService {

    private CategoryRepository categoryRepository;

    private CategoryMapper categoryMapper;

    private CategoryValidator categoryValidator;

    public CategoryServiceImpl(CategoryRepository categoryRepository , CategoryMapper categoryMapper, CategoryValidator categoryValidator){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.categoryValidator = categoryValidator;
    }

    @Override
    public CategoryResponseDto createCategory(@Valid CategoryRequestDto categoriesRequest) {
        categoryValidator.validateCreateRequest(categoriesRequest);
        Category category = categoryMapper.toEntity(categoriesRequest);
        categoryRepository.save(category);
        return categoryMapper.toDto(category);

    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoriesRequest) {
        categoryValidator.validateUpdateRequest(id, categoriesRequest);
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
            category.setName(categoriesRequest.getName());
            categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (!categoryOptional.isPresent()) {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
        categoryRepository.delete(categoryOptional.get());
    }

    @Override
    public Page<CategoryResponseDto> allCategories(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(categoryMapper::toDto);
    }

    @Override
    public Page<CategoryResponseDto> getCategoriesByName(String name, Pageable pageable) {
        Page<Category> categories = categoryRepository.findByName(name,pageable);
        if (categories == null) {
            throw new ResourceNotFoundException("Categories not found");
        }
        return categories.map(categoryMapper::toDto);
    }
}
