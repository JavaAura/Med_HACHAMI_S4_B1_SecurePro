package com.yc.SecurePro.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yc.SecurePro.dto.req.ProductRequestDto;
import com.yc.SecurePro.dto.res.ProductResponseDto;
import com.yc.SecurePro.entity.Category;
import com.yc.SecurePro.entity.Product;
import com.yc.SecurePro.exception.ResourceNotFoundException;
import com.yc.SecurePro.mapper.ProductMapper;
import com.yc.SecurePro.repository.CategoryRepository;
import com.yc.SecurePro.repository.ProductRepository;
import com.yc.SecurePro.service.Interfaces.IProductService;
import com.yc.SecurePro.validator.ProductValidator;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ProductServiceImpl implements IProductService {
    private static final Logger log = LogManager.getLogger(ProductServiceImpl.class);

    private CategoryRepository categoryRepository;

    private ProductRepository productRepository;


    private ProductMapper productMapper;

    private ProductValidator productValidator;

    public ProductServiceImpl(CategoryRepository categoryRepository, ProductMapper productMapper, ProductRepository productRepository,ProductValidator productValidator){
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productValidator = productValidator;
    }

    @Transactional
    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequest) {
        log.info("Creating product with designation: {}", productRequest.getDesignation());

        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        log.info("Category found: {}", category.getName());

        // Product product = new Product();
        // product.setDesignation(productRequest.getDesignation());
        // product.setPrix(productRequest.getPrix());
        // product.setQuantity(productRequest.getQuantity());
        // product.setCategory(category); 

        Product product = productMapper.toEntity(productRequest, category);
        // if(category !=null){
        //     product.setCategory(category);
        //     log.info(" product: {}", product);
            
        // }

        log.info("Saving product: {}", product);
        Product savedProduct = productRepository.save(product);

        log.info("Product saved: {}", savedProduct);
        return productMapper.toDto(savedProduct);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productRequest.getCategoryId()));

        Product updatedProduct = productMapper.toEntity(productRequest, category);
        existingProduct.setDesignation(updatedProduct.getDesignation());
        existingProduct.setPrix(updatedProduct.getPrix());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        existingProduct.setCategory(updatedProduct.getCategory());

        Product savedProduct = productRepository.save(existingProduct);

        return productMapper.toDto(savedProduct);
    }

    @Override
    public void deleteProducts(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }

        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductResponseDto> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(productMapper::toDto);
    }

    @Override
    public Page<ProductResponseDto> getProductsByDesignation(String designation, Pageable pageable) {
        Page<Product> products = productRepository.findByDesignationContainingIgnoreCase(designation, pageable);
        return products.map(productMapper::toDto);
    }

    @Override
    public Page<ProductResponseDto> getProductsByCategory(Long categoryId, Pageable pageable) {
        Page<Product> products = productRepository.findByCategoryId(categoryId, pageable);
        return products.map(productMapper::toDto);
    }


}
