package com.graccasoft.bookspa.repository;

import com.graccasoft.bookspa.model.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

    List<Treatment> findAllByTenant_Id(Long tenantId);
    List<Treatment> findAllByTenant_IdAndIsPromotion(Long tenantId, Boolean isPromotion);
    List<Treatment> findAllByTenant_IdAndCategory_id(Long tenantId, Long categoryId);
}