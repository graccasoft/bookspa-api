package com.graccasoft.redkokia.model.mapper;

import com.graccasoft.redkokia.model.dto.TenantDto;
import com.graccasoft.redkokia.model.entity.Tenant;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-13T19:32:31+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class TenantMapperImpl implements TenantMapper {

    @Override
    public Tenant toEntity(TenantDto dto) {
        if ( dto == null ) {
            return null;
        }

        Tenant tenant = new Tenant();

        tenant.setId( dto.id() );
        tenant.setCompanyName( dto.companyName() );
        tenant.setCompanyPhone( dto.companyPhone() );
        tenant.setCompanyEmail( dto.companyEmail() );
        tenant.setCompanyAddress( dto.companyAddress() );
        tenant.setContactName( dto.contactName() );
        tenant.setContactPhone( dto.contactPhone() );

        return tenant;
    }

    @Override
    public TenantDto toDto(Tenant entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String companyName = null;
        String companyPhone = null;
        String companyEmail = null;
        String companyAddress = null;
        String contactName = null;
        String contactPhone = null;

        id = entity.getId();
        companyName = entity.getCompanyName();
        companyPhone = entity.getCompanyPhone();
        companyEmail = entity.getCompanyEmail();
        companyAddress = entity.getCompanyAddress();
        contactName = entity.getContactName();
        contactPhone = entity.getContactPhone();

        TenantDto tenantDto = new TenantDto( id, companyName, companyPhone, companyEmail, companyAddress, contactName, contactPhone );

        return tenantDto;
    }

    @Override
    public void updateEntity(TenantDto dto, Tenant entity) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.id() );
        entity.setCompanyName( dto.companyName() );
        entity.setCompanyPhone( dto.companyPhone() );
        entity.setCompanyEmail( dto.companyEmail() );
        entity.setCompanyAddress( dto.companyAddress() );
        entity.setContactName( dto.contactName() );
        entity.setContactPhone( dto.contactPhone() );
    }

    @Override
    public List<TenantDto> toDtoList(List<Tenant> entities) {
        if ( entities == null ) {
            return null;
        }

        List<TenantDto> list = new ArrayList<TenantDto>( entities.size() );
        for ( Tenant tenant : entities ) {
            list.add( toDto( tenant ) );
        }

        return list;
    }
}
