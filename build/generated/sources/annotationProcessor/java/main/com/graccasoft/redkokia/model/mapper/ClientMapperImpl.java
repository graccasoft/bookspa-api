package com.graccasoft.redkokia.model.mapper;

import com.graccasoft.redkokia.model.dto.ClientDto;
import com.graccasoft.redkokia.model.dto.TenantDto;
import com.graccasoft.redkokia.model.entity.Client;
import com.graccasoft.redkokia.model.entity.Tenant;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-08T15:00:45+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client toEntity(ClientDto dto) {
        if ( dto == null ) {
            return null;
        }

        Client client = new Client();

        client.setId( dto.id() );
        client.setFirstName( dto.firstName() );
        client.setSurname( dto.surname() );
        client.setPhoneNumber( dto.phoneNumber() );
        client.setEmail( dto.email() );
        client.setAddress( dto.address() );
        client.setCity( dto.city() );
        client.setCountry( dto.country() );
        client.setTenant( tenantDtoToTenant( dto.tenant() ) );

        return client;
    }

    @Override
    public ClientDto toDto(Client entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String firstName = null;
        String surname = null;
        String phoneNumber = null;
        String email = null;
        String address = null;
        String city = null;
        String country = null;
        TenantDto tenant = null;

        id = entity.getId();
        firstName = entity.getFirstName();
        surname = entity.getSurname();
        phoneNumber = entity.getPhoneNumber();
        email = entity.getEmail();
        address = entity.getAddress();
        city = entity.getCity();
        country = entity.getCountry();
        tenant = tenantToTenantDto( entity.getTenant() );

        ClientDto clientDto = new ClientDto( id, firstName, surname, phoneNumber, email, address, city, country, tenant );

        return clientDto;
    }

    @Override
    public void updateEntity(ClientDto dto, Client entity) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.id() );
        entity.setFirstName( dto.firstName() );
        entity.setSurname( dto.surname() );
        entity.setPhoneNumber( dto.phoneNumber() );
        entity.setEmail( dto.email() );
        entity.setAddress( dto.address() );
        entity.setCity( dto.city() );
        entity.setCountry( dto.country() );
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
    public List<ClientDto> toDtoList(List<Client> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ClientDto> list = new ArrayList<ClientDto>( entities.size() );
        for ( Client client : entities ) {
            list.add( toDto( client ) );
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
