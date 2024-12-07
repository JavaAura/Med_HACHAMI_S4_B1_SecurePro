package com.yc.SecurePro.mapper;


import com.yc.SecurePro.dto.req.CategoryRequestDto;
import com.yc.SecurePro.dto.res.CategoryResponseDto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.yc.SecurePro.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toEntity(CategoryRequestDto categoryRequestDto);


    CategoryResponseDto toDto(Category category);

}
