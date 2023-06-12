package com.graccasoft.redkokia.service;

import com.graccasoft.redkokia.exception.RecordDoesNotExistException;
import com.graccasoft.redkokia.model.dto.BookingDto;
import com.graccasoft.redkokia.model.dto.TimeSlot;
import com.graccasoft.redkokia.model.entity.Booking;
import com.graccasoft.redkokia.model.entity.Client;
import com.graccasoft.redkokia.model.mapper.BookingMapper;
import com.graccasoft.redkokia.model.mapper.ClientMapper;
import com.graccasoft.redkokia.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final FreeTimeFinderService freeTimeFinderService;

    public BookingDto saveBooking(BookingDto bookingDto){
        //todo validate dates, etc
        Booking booking = bookingMapper.toEntity( bookingDto );

        //use existing client
        Client client = clientService.findByEmail(bookingDto.client().email());
        if ( client == null ){
            client = clientMapper.toEntity( clientService.saveClient(bookingDto.client()) );
        }
        booking.setClient( client );

        Booking savedBooking = bookingRepository.save( booking );
        return bookingMapper.toDto( savedBooking );

    }

    public List<BookingDto> getBookings(Long tenantId){
        return bookingMapper.toDtoList( bookingRepository.findAllByTreatment_Tenant_Id(tenantId) );
    }

    public BookingDto getBooking(Long bookingId){
        return bookingMapper.toDto(
                bookingRepository
                        .findById(bookingId)
                        .orElseThrow(()-> new RecordDoesNotExistException("Booking with ID not found"))
        );
    }

    public void updateStatus(BookingDto bookingDto){
        Booking booking = bookingRepository.findById(bookingDto.id())
                .orElseThrow(()-> new RecordDoesNotExistException("Booking with ID not found"));

        booking.setStatus( bookingDto.status() );
        bookingRepository.save(booking);
    }

    public List<TimeSlot> getAvailableTimeSlots(Date startDate, Long tenantId, Integer duration){
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(startDate);
        endDate.set(Calendar.HOUR, 23);
        endDate.set(Calendar.MINUTE, 59);

        List<Booking> currentBookings = bookingRepository.findAllByTreatment_Tenant_IdAndBookingDateBetween(tenantId, startDate, endDate.getTime());
        List<TimeSlot> reservedTimeSlots = currentBookings.stream()
                .map(this::bookingToTimeSlot)
                .toList();

        //start time to be at 8am, and end time to be 10 hours from start
        //todo: start time and operation hours to come from tenant settings
        LocalDateTime startTime = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusHours(8);
        LocalDateTime endTime = startTime.plusHours(10);

        return freeTimeFinderService.findFreeTimeSlots( new ArrayList<>(reservedTimeSlots), startTime, endTime, duration );

    }

    public TimeSlot bookingToTimeSlot(Booking booking){

        LocalDateTime startTime = booking.getBookingDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endTime = startTime.plusMinutes(booking.getDuration());

        return new TimeSlot(startTime, endTime);
    }


    public void cancelBooking(Long bookingId){
        bookingRepository.deleteById(bookingId);
    }
}
