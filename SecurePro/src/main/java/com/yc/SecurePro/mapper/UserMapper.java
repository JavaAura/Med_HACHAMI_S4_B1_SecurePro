package com.yc.SecurePro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

     UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

//      AppUser toEntity(RegisterRequestDto dto);

      // AuthenticationResponseDto toDTO(AppUser user);

}
