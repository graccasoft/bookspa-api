package com.graccasoft.redkokia.model.mapper;

import com.graccasoft.redkokia.model.dto.BookingDto;
import com.graccasoft.redkokia.model.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper extends EntityMapper<Booking, BookingDto> {
}
