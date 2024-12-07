package com.yc.SecurePro.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.yc.SecurePro.dto.req.ProductRequestDto;
import com.yc.SecurePro.dto.res.ProductResponseDto;
import com.yc.SecurePro.exception.ResourceNotFoundException;
import com.yc.SecurePro.service.Interfaces.IProductService;

@RestController
@RequestMapping("api/")
public class ProductController {
    private IProductService productService;

    public ProductController(IProductService productService){
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/products")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequest) {
        ProductResponseDto productResponse = productService.createProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);   
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/products/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
        @PathVariable Long id, @RequestBody ProductRequestDto productRequest) {
        try {
            ProductResponseDto productResponse = productService.updateProduct(id, productRequest);
            return ResponseEntity.ok(productResponse);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductResponseDto());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProducts(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted successfully");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    @GetMapping("/user/products")
    public ResponseEntity<Page<ProductResponseDto>> getAllProducts(Pageable pageable) {
        Page<ProductResponseDto> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/user/products/designation")
    public ResponseEntity<Page<ProductResponseDto>> getProductsByDesignation(
        @RequestParam String designation, Pageable pageable) {
        Page<ProductResponseDto> products = productService.getProductsByDesignation(designation, pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/user/products/category/{categoryId}")
    public ResponseEntity<Page<ProductResponseDto>> getProductsByCategory(
            @PathVariable Long categoryId, Pageable pageable) {
        Page<ProductResponseDto> products = productService.getProductsByCategory(categoryId, pageable);
        return ResponseEntity.ok(products);
    }
}
