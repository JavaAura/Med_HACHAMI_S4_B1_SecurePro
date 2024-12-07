package com.yc.SecurePro.service.Interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yc.SecurePro.dto.req.CategoryRequestDto;
import com.yc.SecurePro.dto.res.CategoryResponseDto;

public interface ICategoryService {
    CategoryResponseDto createCategory(CategoryRequestDto categoriesRequest);
    CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoriesRequest);
    void deleteCategory(Long id);
    Page<CategoryResponseDto> allCategories(Pageable pageable);

    Page<CategoryResponseDto> getCategoriesByName(String name, Pageable pageable);
}
