package com.graccasoft.redkokia.controller;


import com.graccasoft.redkokia.helper.CSVHelper;
import com.graccasoft.redkokia.model.dto.ClientDto;
import com.graccasoft.redkokia.model.dto.EmployeeDto;
import com.graccasoft.redkokia.model.dto.GenericResponse;
import com.graccasoft.redkokia.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @PutMapping
    public GenericResponse toggleAvailability(@RequestBody EmployeeDto employeeDto){
        employeeService.toggleAvailability (employeeDto);
        return new GenericResponse(true, "Employee availability has been updated");
    }

    @GetMapping
    public List<EmployeeDto> getEmployees(@RequestParam Long tenantId){
        return employeeService.getEmployees(tenantId);
    }

    @GetMapping("employees-csv")
    public ResponseEntity<Resource> getFile(@RequestParam Long tenantId) {
        String filename = "clients.csv";
        List<EmployeeDto> employees= employeeService.getEmployees(tenantId);
        InputStreamResource file = new InputStreamResource(CSVHelper.employeesToCsv (employees));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @DeleteMapping("{employeeId}")
    public GenericResponse deleteEmployee(@PathVariable Long employeeId){
        employeeService.deleteEmployee (employeeId);
        return new GenericResponse(true, "Employee has been deleted");
    }
}
