package com.graccasoft.bookspa.model.mapper;

import com.graccasoft.bookspa.model.dto.ClientDto;
import com.graccasoft.bookspa.model.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends EntityMapper<Client, ClientDto> {
}
