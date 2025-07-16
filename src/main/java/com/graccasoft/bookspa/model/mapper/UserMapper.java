package com.graccasoft.bookspa.model.mapper;

import com.graccasoft.bookspa.model.dto.RegisterUserDto;
import com.graccasoft.bookspa.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User, RegisterUserDto> {
}
