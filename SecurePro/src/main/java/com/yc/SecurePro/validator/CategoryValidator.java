package com.yc.SecurePro.validator;


import org.springframework.stereotype.Component;

import com.yc.SecurePro.dto.req.CategoryRequestDto;

@Component
public class CategoryValidator {

    public void validateCreateRequest(CategoryRequestDto categoriesRequest) {
        validateName(categoriesRequest.getName());
        validateDescription(categoriesRequest.getDescription());
    }


    public void validateUpdateRequest(Long id, CategoryRequestDto request) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        if (request == null) {
            throw new RuntimeException("Categories request cannot be null");
        }
        validateName(request.getName());
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        if (name.length() < 2 || name.length() > 50) {
            throw new IllegalArgumentException("Category name must be between 2 and 50 characters");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Category description cannot be null or empty");
        }
    }

}
