package com.yc.SecurePro.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yc.SecurePro.entity.Product;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByDesignationContainingIgnoreCase(String designation, Pageable pageable);
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
}
