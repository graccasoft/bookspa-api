package com.graccasoft.redkokia.repository;

import com.graccasoft.redkokia.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByTreatments_Tenant_Id(Long tenantId);

    List<Booking> findAllByTreatments_Tenant_IdAndBookingDateBetween(Long tenantId, Date startDate, Date endDate);
    List<Booking> findAllByTreatments_Tenant_IdAndEmployee_IdAndBookingDateBetween(Long tenantId, Long employeeId, Date startDate, Date endDate);

    Optional<Booking> findByReference(String reference);

    void deleteByReference(String reference);
}