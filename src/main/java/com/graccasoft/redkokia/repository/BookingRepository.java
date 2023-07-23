package com.graccasoft.redkokia.repository;

import com.graccasoft.redkokia.model.entity.Booking;
import com.graccasoft.redkokia.model.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByTreatments_Tenant_Id(Long tenantId);

    List<Booking> findAllByTreatments_Tenant_IdAndCreatedAtBetween(Long tenantId, Date startDate, Date endDate);
    List<Booking> findAllByTreatments_Tenant_IdAndCreatedAtBetweenAndStatusNotIn(Long treatments_tenant_id, Date createdAt, Date createdAt2, Collection<BookingStatus> status);
    List<Booking> findAllByTreatments_Tenant_IdAndEmployee_IdAndBookingDateBetween(Long tenantId, Long employeeId, Date startDate, Date endDate);

    Optional<Booking> findByReference(String reference);

    void deleteByReference(String reference);
}