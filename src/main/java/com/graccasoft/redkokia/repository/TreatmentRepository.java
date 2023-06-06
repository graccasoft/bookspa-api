package com.graccasoft.redkokia.repository;

import com.graccasoft.redkokia.model.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

    List<Treatment> findAllByTenant_Id(Long tenantId);
}