package com.graccasoft.bookspa.repository;

import com.graccasoft.bookspa.model.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

    Optional<Tenant> findByReference(String reference);
    List<Tenant> findAllByIsDeleted(Boolean isDeleted);

}