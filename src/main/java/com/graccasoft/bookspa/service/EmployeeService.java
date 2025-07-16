package com.graccasoft.bookspa.service;

import com.graccasoft.bookspa.model.dto.EmployeeDto;
import com.graccasoft.bookspa.model.entity.Employee;
import com.graccasoft.bookspa.model.mapper.EmployeeMapper;
import com.graccasoft.bookspa.exception.RecordNotFoundException;
import com.graccasoft.bookspa.repository.EmployeeRepository;
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
        return employeeMapper.toDtoList( employeeRepository.findAllByTenant_IdAndIsDeleted(tenantId, false) );
    }
    public List<EmployeeDto> getEmployees(Long tenantId, boolean availability){
        return employeeMapper.toDtoList( employeeRepository.findAllByTenant_IdAndIsAvailable(tenantId, availability) );
    }

    public void toggleAvailability(EmployeeDto employeeDto){
        Employee employee = employeeRepository.findById(employeeDto.id())
                .orElseThrow(()-> new RecordNotFoundException("Employee not found"));

        employee.setIsAvailable( employeeDto.isAvailable() );
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new RecordNotFoundException("Employee not found"));

        employee.setIsDeleted(true);
        employee.setIsAvailable(false);
        employeeRepository.save(employee);
    }

}
