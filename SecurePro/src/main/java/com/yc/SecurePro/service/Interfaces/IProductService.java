package com.yc.SecurePro.service.Interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yc.SecurePro.dto.req.ProductRequestDto;
import com.yc.SecurePro.dto.res.ProductResponseDto;
public interface IProductService {
    ProductResponseDto createProduct(ProductRequestDto productRequest);
    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequest);
    void deleteProducts(Long id);
    Page<ProductResponseDto> getAllProducts(Pageable pageable);
    Page<ProductResponseDto> getProductsByDesignation(String designation, Pageable pageable);
    Page<ProductResponseDto> getProductsByCategory(Long categoryId, Pageable pageable);
}
