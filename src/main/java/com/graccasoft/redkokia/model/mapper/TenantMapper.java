package com.graccasoft.redkokia.model.mapper;

import com.graccasoft.redkokia.model.dto.TenantDto;
import com.graccasoft.redkokia.model.entity.Tenant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TenantMapper extends EntityMapper<Tenant, TenantDto> {
}
