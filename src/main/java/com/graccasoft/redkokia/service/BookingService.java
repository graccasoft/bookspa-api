package com.graccasoft.redkokia.service;

import com.graccasoft.redkokia.exception.RecordDoesNotExistException;
import com.graccasoft.redkokia.model.dto.BookingDto;
import com.graccasoft.redkokia.model.dto.BookingReportDto;
import com.graccasoft.redkokia.model.dto.TimeSlot;
import com.graccasoft.redkokia.model.entity.Booking;
import com.graccasoft.redkokia.model.entity.Client;
import com.graccasoft.redkokia.model.entity.Tenant;
import com.graccasoft.redkokia.model.enums.BookingStatus;
import com.graccasoft.redkokia.model.mapper.BookingMapper;
import com.graccasoft.redkokia.model.mapper.BookingReportMapper;
import com.graccasoft.redkokia.model.mapper.ClientMapper;
import com.graccasoft.redkokia.repository.BookingRepository;
import com.graccasoft.redkokia.repository.TenantRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class BookingService {

    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final FreeTimeFinderService freeTimeFinderService;
    private final SpringTemplateEngine springTemplateEngine;
    private final EmailSenderService emailSenderService;
    private final BookingReportMapper bookingReportMapper;
    private final TenantRepository tenantRepository;
    private final PdfGeneratorService pdfGeneratorService;

    public BookingDto saveBooking(BookingDto bookingDto){
        //todo validate dates, etc
        Booking booking = bookingMapper.toEntity( bookingDto );

        //use existing client
        Client client = clientService.findByEmail(bookingDto.client().email());
        if ( client == null ){
            client = clientMapper.toEntity( clientService.saveClient(bookingDto.client()) );
        }
        booking.setClient( client );
        booking.setReference(generateBookingReference());
        Booking savedBooking = bookingRepository.save( booking );

        sendBookingEmail(savedBooking);
        return bookingMapper.toDto( savedBooking );

    }

    //todo add this to a utility or helper class
    private String generateBookingReference(){
        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(candidateChars.length());
            sb.append(candidateChars.charAt(index));
        }
        return sb.toString();
    }

    private void sendBookingEmail(Booking booking){
        Context emailContext = new Context();
        emailContext.setVariable("booking", booking);
        emailContext.setVariable("tenant", booking.getTreatments().get(0).getTenant());
        final String htmlContent = this.springTemplateEngine.process("customer-booking-email", emailContext);

        emailSenderService.sendEmail(booking.getClient().getEmail(), "Your Booking on RedKokia", htmlContent);
    }

    private void sendCancelledBookingEmail(Booking booking){
        Context emailContext = new Context();
        emailContext.setVariable("booking", booking);
        emailContext.setVariable("tenant", booking.getTreatments().get(0).getTenant());
        final String htmlContent = this.springTemplateEngine.process("customer-booking-cancelled-email", emailContext);

        emailSenderService.sendEmail(booking.getClient().getEmail(), "Your Booking on RedKokia", htmlContent);
    }

    public List<BookingDto> getBookings(Long tenantId){
        return bookingMapper.toDtoList( bookingRepository.findAllByTreatments_Tenant_Id(tenantId) );
    }

    public BookingDto getBooking(Long bookingId){
        return bookingMapper.toDto(
                bookingRepository
                        .findById(bookingId)
                        .orElseThrow(()-> new RecordDoesNotExistException("Booking with ID not found"))
        );
    }

    public BookingDto getBooking(String reference){
        return bookingMapper.toDto(
                bookingRepository
                        .findByReference(reference)
                        .orElseThrow(()-> new RecordDoesNotExistException("Booking with ID not found"))
        );
    }

    public void updateStatus(BookingDto bookingDto){
        Booking booking = bookingRepository.findById(bookingDto.id())
                .orElseThrow(()-> new RecordDoesNotExistException("Booking with ID not found"));

        booking.setStatus( bookingDto.status() );
        bookingRepository.save(booking);
    }

    public void updatePaymentMethod(BookingDto bookingDto){
        Booking booking = bookingRepository.findById(bookingDto.id())
                .orElseThrow(()-> new RecordDoesNotExistException("Booking with ID not found"));

        booking.setPaymentMethod ( bookingDto.paymentMethod());
        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);
    }

    public List<TimeSlot> getAvailableTimeSlots(Date startDate, Long tenantId, Long employeeId, Integer duration){
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(startDate);
        endDate.set(Calendar.HOUR, 23);
        endDate.set(Calendar.MINUTE, 59);

        List<Booking> currentBookings = bookingRepository
                .findAllByTreatments_Tenant_IdAndEmployee_IdAndBookingDateBetween(tenantId,employeeId, startDate, endDate.getTime());
        List<TimeSlot> reservedTimeSlots = currentBookings.stream()
                .filter(booking -> booking.getStatus() != BookingStatus.CANCELLED)
                .map(this::bookingToTimeSlot)
                .toList();

        LocalDateTime startTime = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endTime = startTime;
        Tenant tenant = tenantRepository.getReferenceById(tenantId);
        if( tenant.getOpeningTime() != null ){
            int[] times = timeStrToInt(tenant.getOpeningTime());
            startTime = startTime.plusHours(times[0]);
            startTime = startTime.plusMinutes(times[1]);
        }

        if( tenant.getClosingTime() != null ){
            int[] times = timeStrToInt(tenant.getClosingTime());
            endTime = endTime.plusHours(times[0]);
            endTime = endTime.plusMinutes(times[1]);
        }else{
            endTime = endTime.plusHours(10);
        }

        return freeTimeFinderService.findFreeTimeSlots( new ArrayList<>(reservedTimeSlots), startTime, endTime, duration );

    }

    private int[] timeStrToInt(String timeStr){
        int[] time = new int[]{8,16};

        String[] tmp = timeStr.split(":");
        if( tmp.length == 2 ){
            time[0] = Integer.parseInt( tmp[0] );
            time[1] = Integer.parseInt( tmp[1] );
        }
        return time;
    }

    public TimeSlot bookingToTimeSlot(Booking booking){

        LocalDateTime startTime = booking.getBookingDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endTime = startTime.plusMinutes(booking.getDuration());

        return new TimeSlot(startTime, endTime);
    }

    @Transactional
    public void cancelBooking(String reference){
        Booking booking = bookingRepository.findByReference (reference).
                orElseThrow(()-> new RecordDoesNotExistException("Booking not found"));
        sendCancelledBookingEmail(booking);
        booking.setStatus( BookingStatus.CANCELLED );
        bookingRepository.save(booking);
    }

    public void cancelBooking(Long bookingId){
        Booking booking = bookingRepository.findById(bookingId).
                orElseThrow(()-> new RecordDoesNotExistException("Booking not found"));
        sendCancelledBookingEmail(booking);
        booking.setStatus( BookingStatus.CANCELLED );
        bookingRepository.save(booking);
    }

    public List<BookingReportDto> bookingsReport(Long tenantId, Date startDate, Date endDate){
        return bookingRepository.findAllByTreatments_Tenant_IdAndCreatedAtBetweenAndStatusNotIn(tenantId, startDate, endDate, Collections.singletonList(BookingStatus.CANCELLED))
                .stream()
                .map(bookingReportMapper)
                .toList();
    }

    public Resource bookingsReportPdf(Long tenantId, Date startDate, Date endDate){
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(()-> new RecordDoesNotExistException("Tenant not found"));
        List<BookingReportDto> bookings = bookingRepository.findAllByTreatments_Tenant_IdAndCreatedAtBetweenAndStatusNotIn(tenantId, startDate, endDate, Collections.singletonList(BookingStatus.CANCELLED))
                .stream()
                .map(bookingReportMapper)
                .toList();

        return pdfGeneratorService.getBookingsReportPdf(bookings, tenant);
    }
}
