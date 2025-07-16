package com.graccasoft.bookspa.model.mapper;

import com.graccasoft.bookspa.model.dto.BookingDto;
import com.graccasoft.bookspa.model.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper extends EntityMapper<Booking, BookingDto> {
}
