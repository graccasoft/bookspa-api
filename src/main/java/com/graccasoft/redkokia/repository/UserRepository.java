package com.graccasoft.redkokia.repository;

import com.graccasoft.redkokia.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findAllByTenant_Id(Long tenantId);
}
