package com.graccasoft.redkokia.model.mapper;

import com.graccasoft.redkokia.model.dto.EmployeeDto;
import com.graccasoft.redkokia.model.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<Employee, EmployeeDto> {
}
