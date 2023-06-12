package com.graccasoft.redkokia.service;

import com.graccasoft.redkokia.model.dto.TimeSlot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class FreeTimeFinderServiceTest {

    private FreeTimeFinderService underTest;

    @BeforeEach
    void setUp() {
        underTest = new FreeTimeFinderService();
    }

    @Test
    void shouldGetFreeSlots(){
        List<TimeSlot> bookings = new ArrayList<>();
        //bookings.add(new TimeSlot(LocalDateTime.of(2023, 6, 9, 10, 0), LocalDateTime.of(2023, 6, 9, 11, 0)));
        //bookings.add(new TimeSlot(LocalDateTime.of(2023, 6, 9, 12, 0), LocalDateTime.of(2023, 6, 9, 13, 0)));
        //bookings.add(new TimeSlot(LocalDateTime.of(2023, 6, 9, 14, 0), LocalDateTime.of(2023, 6, 9, 15, 0)));

        LocalDateTime start_time = LocalDateTime.of(2023, 6, 9, 9, 0);
        LocalDateTime end_time = LocalDateTime.of(2023, 6, 9, 16, 0);
        int min_duration = 30;

        List<TimeSlot> freeTimeSlots = underTest.findFreeTimeSlots (bookings, start_time, end_time, min_duration);

        for (TimeSlot free_time_slot : freeTimeSlots) {
            System.out.println(free_time_slot.startTime() + " - " + free_time_slot.endTime());
        }

        Assertions.assertEquals(13, freeTimeSlots.size());
    }
}