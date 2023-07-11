package com.graccasoft.redkokia.service;

import com.graccasoft.redkokia.exception.RecordDoesNotExistException;
import com.graccasoft.redkokia.model.dto.EmployeeDto;
import com.graccasoft.redkokia.model.entity.Employee;
import com.graccasoft.redkokia.model.mapper.EmployeeMapper;
import com.graccasoft.redkokia.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    public EmployeeDto saveEmployee(EmployeeDto employeeDto){
        Employee savedEmployee = employeeRepository.save( employeeMapper.toEntity(employeeDto) );
        return employeeMapper.toDto(savedEmployee);
    }

    public List<EmployeeDto> getEmployees(Long tenantId){
        return employeeMapper.toDtoList( employeeRepository.findAllByTenant_Id(tenantId) );
    }
    public List<EmployeeDto> getEmployees(Long tenantId, boolean availability){
        return employeeMapper.toDtoList( employeeRepository.findAllByTenant_IdAndIsAvailable(tenantId, availability) );
    }

    public void toggleAvailability(EmployeeDto employeeDto){
        Employee employee = employeeRepository.findById(employeeDto.id())
                .orElseThrow(()-> new RecordDoesNotExistException("Employee not found"));

        employee.setIsAvailable( employeeDto.isAvailable() );
        employeeRepository.save(employee);
    }
}
