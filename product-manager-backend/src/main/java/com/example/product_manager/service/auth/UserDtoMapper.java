package com.example.product_manager.service.auth;

import com.example.product_manager.model.UserEntity;
import com.example.product_manager.service.auth.model.UserDto;

public class UserDtoMapper {
  public static UserDto toUserDto(UserEntity entity) {
    return new UserDto(entity.id(), entity.username(), entity.role());
  }
}
