package com.graccasoft.redkokia.model.mapper;

import com.graccasoft.redkokia.model.dto.ClientDto;
import com.graccasoft.redkokia.model.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends EntityMapper<Client, ClientDto> {
}
