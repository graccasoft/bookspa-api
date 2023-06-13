package com.graccasoft.redkokia.model.mapper;

import com.graccasoft.redkokia.model.dto.RegisterUserDto;
import com.graccasoft.redkokia.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User, RegisterUserDto> {
}
