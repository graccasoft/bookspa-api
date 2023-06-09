package com.graccasoft.redkokia.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class FreeTimeFinderService {
    public static List<TimeSlot> findFreeTimeSlots(List<TimeSlot> bookings, LocalDateTime start_time, LocalDateTime end_time, int min_duration) {
        // Sort the bookings by start time
        bookings.sort((a, b) -> a.start_time.compareTo(b.start_time));

        List<TimeSlot> free_time_slots = new ArrayList<>();
        LocalDateTime current_time = start_time;

        // Iterate through the sorted bookings and find free time slots
        for (TimeSlot booking : bookings) {
            if (current_time.plusMinutes(min_duration).isBefore(booking.start_time)) {
                free_time_slots.add(new TimeSlot(current_time, booking.start_time));
            }
            current_time = current_time.isAfter(booking.end_time) ? current_time : booking.end_time;
        }

        if (current_time.plusMinutes(min_duration).isBefore(end_time)) {
            free_time_slots.add(new TimeSlot(current_time, end_time));
        }

        return free_time_slots;
    }

    public static void main(String[] args) {
        List<TimeSlot> bookings = new ArrayList<>();
        bookings.add(new TimeSlot(LocalDateTime.of(2023, 6, 9, 10, 0), LocalDateTime.of(2023, 6, 9, 11, 0)));
        bookings.add(new TimeSlot(LocalDateTime.of(2023, 6, 9, 12, 0), LocalDateTime.of(2023, 6, 9, 13, 0)));
        bookings.add(new TimeSlot(LocalDateTime.of(2023, 6, 9, 14, 0), LocalDateTime.of(2023, 6, 9, 15, 0)));

        LocalDateTime start_time = LocalDateTime.of(2023, 6, 9, 9, 0);
        LocalDateTime end_time = LocalDateTime.of(2023, 6, 9, 16, 0);
        int min_duration = 30;

        List<TimeSlot> free_time_slots = findFreeTimeSlots(bookings, start_time, end_time, min_duration);

        for (TimeSlot free_time_slot : free_time_slots) {
            System.out.println(free_time_slot.start_time + " - " + free_time_slot.end_time);
        }
    }
}

class TimeSlot {
    public LocalDateTime start_time;
    public LocalDateTime end_time;

    public TimeSlot(LocalDateTime start_time, LocalDateTime end_time) {
        this.start_time = start_time;
        this.end_time = end_time;
    }
}