package com.graccasoft.redkokia.model.mapper;

import com.graccasoft.redkokia.model.dto.TenantDto;
import com.graccasoft.redkokia.model.dto.TreatmentDto;
import com.graccasoft.redkokia.model.entity.Tenant;
import com.graccasoft.redkokia.model.entity.Treatment;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-12T19:04:50+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class TreatmentMapperImpl implements TreatmentMapper {

    @Override
    public Treatment toEntity(TreatmentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Treatment treatment = new Treatment();

        treatment.setId( dto.id() );
        treatment.setName( dto.name() );
        treatment.setDescription( dto.description() );
        treatment.setPrice( dto.price() );
        treatment.setMinimumDuration( dto.minimumDuration() );
        treatment.setMaximumDuration( dto.maximumDuration() );
        treatment.setTenant( tenantDtoToTenant( dto.tenant() ) );

        return treatment;
    }

    @Override
    public TreatmentDto toDto(Treatment entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;
        BigDecimal price = null;
        Integer minimumDuration = null;
        Integer maximumDuration = null;
        TenantDto tenant = null;

        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        minimumDuration = entity.getMinimumDuration();
        maximumDuration = entity.getMaximumDuration();
        tenant = tenantToTenantDto( entity.getTenant() );

        TreatmentDto treatmentDto = new TreatmentDto( id, name, description, price, minimumDuration, maximumDuration, tenant );

        return treatmentDto;
    }

    @Override
    public void updateEntity(TreatmentDto dto, Treatment entity) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.id() );
        entity.setName( dto.name() );
        entity.setDescription( dto.description() );
        entity.setPrice( dto.price() );
        entity.setMinimumDuration( dto.minimumDuration() );
        entity.setMaximumDuration( dto.maximumDuration() );
        if ( dto.tenant() != null ) {
            if ( entity.getTenant() == null ) {
                entity.setTenant( new Tenant() );
            }
            tenantDtoToTenant1( dto.tenant(), entity.getTenant() );
        }
        else {
            entity.setTenant( null );
        }
    }

    @Override
    public List<TreatmentDto> toDtoList(List<Treatment> entities) {
        if ( entities == null ) {
            return null;
        }

        List<TreatmentDto> list = new ArrayList<TreatmentDto>( entities.size() );
        for ( Treatment treatment : entities ) {
            list.add( toDto( treatment ) );
        }

        return list;
    }

    protected Tenant tenantDtoToTenant(TenantDto tenantDto) {
        if ( tenantDto == null ) {
            return null;
        }

        Tenant tenant = new Tenant();

        tenant.setId( tenantDto.id() );
        tenant.setCompanyName( tenantDto.companyName() );
        tenant.setCompanyPhone( tenantDto.companyPhone() );
        tenant.setCompanyEmail( tenantDto.companyEmail() );
        tenant.setCompanyAddress( tenantDto.companyAddress() );
        tenant.setContactName( tenantDto.contactName() );
        tenant.setContactPhone( tenantDto.contactPhone() );

        return tenant;
    }

    protected TenantDto tenantToTenantDto(Tenant tenant) {
        if ( tenant == null ) {
            return null;
        }

        Long id = null;
        String companyName = null;
        String companyPhone = null;
        String companyEmail = null;
        String companyAddress = null;
        String contactName = null;
        String contactPhone = null;

        id = tenant.getId();
        companyName = tenant.getCompanyName();
        companyPhone = tenant.getCompanyPhone();
        companyEmail = tenant.getCompanyEmail();
        companyAddress = tenant.getCompanyAddress();
        contactName = tenant.getContactName();
        contactPhone = tenant.getContactPhone();

        TenantDto tenantDto = new TenantDto( id, companyName, companyPhone, companyEmail, companyAddress, contactName, contactPhone );

        return tenantDto;
    }

    protected void tenantDtoToTenant1(TenantDto tenantDto, Tenant mappingTarget) {
        if ( tenantDto == null ) {
            return;
        }

        mappingTarget.setId( tenantDto.id() );
        mappingTarget.setCompanyName( tenantDto.companyName() );
        mappingTarget.setCompanyPhone( tenantDto.companyPhone() );
        mappingTarget.setCompanyEmail( tenantDto.companyEmail() );
        mappingTarget.setCompanyAddress( tenantDto.companyAddress() );
        mappingTarget.setContactName( tenantDto.contactName() );
        mappingTarget.setContactPhone( tenantDto.contactPhone() );
    }
}
