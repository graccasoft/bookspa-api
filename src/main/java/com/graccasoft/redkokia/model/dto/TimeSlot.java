package com.graccasoft.redkokia.model.dto;

import java.time.LocalDateTime;

public record TimeSlot(
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
