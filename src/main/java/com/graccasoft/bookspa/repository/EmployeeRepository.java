package com.graccasoft.bookspa.repository;

import com.graccasoft.bookspa.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByTenant_Id(Long tenantId);
    List<Employee> findAllByTenant_IdAndIsDeleted(Long tenant_id, Boolean isDeleted);
    List<Employee> findAllByTenant_IdAndIsAvailable(Long tenantId, boolean isAvailable);
    long countAllByTenant_Id(Long tenantId);
}