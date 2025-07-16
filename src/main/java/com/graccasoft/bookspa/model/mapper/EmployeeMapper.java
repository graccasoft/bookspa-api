package com.graccasoft.bookspa.model.mapper;

import com.graccasoft.bookspa.model.dto.EmployeeDto;
import com.graccasoft.bookspa.model.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<Employee, EmployeeDto> {
}
