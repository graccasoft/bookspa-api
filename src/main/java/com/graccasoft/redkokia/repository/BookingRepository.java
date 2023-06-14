package com.graccasoft.redkokia.repository;

import com.graccasoft.redkokia.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByTreatments_Tenant_Id(Long tenantId);

    List<Booking> findAllByTreatments_Tenant_IdAndBookingDateBetween(Long tenantId, Date startDate, Date endDate);
}