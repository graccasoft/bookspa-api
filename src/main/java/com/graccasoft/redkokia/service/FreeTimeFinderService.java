package com.graccasoft.redkokia.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.graccasoft.redkokia.model.dto.TimeSlot;
import org.springframework.stereotype.Service;

@Service
public class FreeTimeFinderService {
    public  List<TimeSlot> findFreeTimeSlots(List<TimeSlot> bookings, LocalDateTime startTime, LocalDateTime endTime, int minDuration) {
        // Sort the bookings by start time
        bookings.sort((a, b) -> a.startTime().compareTo(b.startTime()));

        List<TimeSlot> freeTimeSlots = new ArrayList<>();
        LocalDateTime currentTime = startTime;

        // Iterate through the time slots in 30 minute intervals and find free time slots
        while (currentTime.plusMinutes(minDuration).isBefore(endTime)) {
            LocalDateTime next_time = currentTime.plusMinutes(minDuration);

            boolean isBooked = false;
            for (TimeSlot booking : bookings) {
                if (booking.startTime().isBefore(next_time) && booking.endTime().isAfter(currentTime)) {
                    isBooked = true;
                    currentTime = booking.endTime();
                    break;
                }
            }

            if (!isBooked) {
                freeTimeSlots.add(new TimeSlot(currentTime, next_time));
                currentTime = next_time;
            }
        }

        return freeTimeSlots;
    }

}
