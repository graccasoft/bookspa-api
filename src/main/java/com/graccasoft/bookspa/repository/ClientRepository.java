package com.graccasoft.bookspa.repository;

import com.graccasoft.bookspa.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);

    List<Client> findAllByTenant_Id(Long tenantId);
    List<Client> findAllByTenant_IdAndIsDeleted(Long tenant_id, Boolean deleted);
}