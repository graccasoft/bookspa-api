package com.graccasoft.redkokia.service;

import com.graccasoft.redkokia.exception.RecordDoesNotExistException;
import com.graccasoft.redkokia.model.dto.BookingDto;
import com.graccasoft.redkokia.model.entity.Booking;
import com.graccasoft.redkokia.model.entity.Client;
import com.graccasoft.redkokia.model.mapper.BookingMapper;
import com.graccasoft.redkokia.model.mapper.ClientMapper;
import com.graccasoft.redkokia.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    private final ClientService clientService;
    private final ClientMapper clientMapper;

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
}
