package com.graccasoft.redkokia.repository;

import com.graccasoft.redkokia.model.entity.TreatmentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentCategoryRepository extends JpaRepository<TreatmentCategory, Long> {
}