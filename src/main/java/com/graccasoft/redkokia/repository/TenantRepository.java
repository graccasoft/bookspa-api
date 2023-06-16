package com.graccasoft.redkokia.repository;

import com.graccasoft.redkokia.model.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

    Optional<Tenant> findByReference(String reference);
}