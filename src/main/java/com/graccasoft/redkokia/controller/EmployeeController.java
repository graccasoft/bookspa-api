package com.graccasoft.redkokia.controller;


import com.graccasoft.redkokia.model.dto.EmployeeDto;
import com.graccasoft.redkokia.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto){
        return employeeService.saveEmployee(employeeDto);
    }

    @GetMapping
    public List<EmployeeDto> getEmployees(@RequestParam Long tenantId){
        return employeeService.getEmployees(tenantId);
    }
}
