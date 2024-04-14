package com.example.bookstore.mappper;

import com.example.bookstore.config.MapperConfig;
import com.example.bookstore.dto.request.UserRegistrationRequestDto;
import com.example.bookstore.dto.response.UserResponseDto;
import com.example.bookstore.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User toModel(UserRegistrationRequestDto requestDto);

    UserResponseDto toDto(User user);
}
