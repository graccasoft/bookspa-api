package com.graccasoft.redkokia.repository;

import com.graccasoft.redkokia.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByTreatment_Tenant_Id(Long tenantId);
}