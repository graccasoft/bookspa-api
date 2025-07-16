package com.graccasoft.bookspa.model.mapper;

import com.graccasoft.bookspa.model.dto.TenantDto;
import com.graccasoft.bookspa.model.entity.Tenant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TenantMapper extends EntityMapper<Tenant, TenantDto> {
}
