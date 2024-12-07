package com.yc.SecurePro.validator;


import org.springframework.stereotype.Component;

import com.yc.SecurePro.dto.req.ProductRequestDto;

@Component
public class ProductValidator {
    public void validateCreateRequest(ProductRequestDto requestDto) {
        validateDesignation(requestDto.getDesignation());
        validatePrix(requestDto.getPrix());
        validateQuantity(requestDto.getQuantity());
        validateCategory(requestDto.getCategoryId());
    }

    public void validateUpdateRequest(Long id, ProductRequestDto requestDto) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        if (requestDto == null) {
            throw new IllegalArgumentException("Produits request cannot be null");
        }
        validateDesignation(requestDto.getDesignation());
        validatePrix(requestDto.getPrix());
        validateQuantity(requestDto.getQuantity());
        validateCategory(requestDto.getCategoryId());
    }

   

    private void validateDesignation(String designation) {
        if (designation == null || designation.trim().isEmpty()) {
            throw new IllegalArgumentException("Designation cannot be null or empty");
        }
        if (designation.length() < 2 || designation.length() > 100) {
            throw new IllegalArgumentException("Designation must be between 2 and 100 characters");
        }
    }

    private void validatePrix(Double prix) {
        if (prix == null || prix <= 0) {
            throw new IllegalArgumentException("Prix must be greater than 0");
        }
    }

    private void validateQuantity(Integer quantite) {
        if (quantite == null || quantite < 0) {
            throw new IllegalArgumentException("Quantite cannot be negative");
        }
    }

    private void validateCategory(Long categorieId) {
        if (categorieId == null) {
            throw new IllegalArgumentException("Categorie ID cannot be null");
        }
    }
}
