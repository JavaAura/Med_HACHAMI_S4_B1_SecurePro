package com.yc.SecurePro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yc.SecurePro.dto.req.ProductRequestDto;
import com.yc.SecurePro.dto.res.ProductResponseDto;
import com.yc.SecurePro.entity.Category;
import com.yc.SecurePro.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    default Product toEntity(ProductRequestDto productRequestDto, Category category) {
        Product product = new Product();
        product.setDesignation(productRequestDto.getDesignation());
        product.setPrix(productRequestDto.getPrix());
        product.setQuantity(productRequestDto.getQuantity());
        product.setCategory(category); // Set the existing Category
        return product;
    }

    @Mapping(source = "category.name", target = "categoryName")
    ProductResponseDto toDto(Product product);
}
