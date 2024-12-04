package com.yc.SecurePro.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yc.SecurePro.dto.req.RegisterRequestDto;
import com.yc.SecurePro.dto.res.AuthenticationResponseDto;
import com.yc.SecurePro.entity.AppUser;

@Mapper
public interface UserMapper {

     UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

     AppUser toEntity(RegisterRequestDto dto);

      AuthenticationResponseDto toDTO(AppUser user);

}
