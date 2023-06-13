package com.graccasoft.redkokia.model.mapper;

import com.graccasoft.redkokia.model.dto.BookingDto;
import com.graccasoft.redkokia.model.dto.ClientDto;
import com.graccasoft.redkokia.model.dto.TenantDto;
import com.graccasoft.redkokia.model.dto.TreatmentDto;
import com.graccasoft.redkokia.model.entity.Booking;
import com.graccasoft.redkokia.model.entity.Client;
import com.graccasoft.redkokia.model.entity.Tenant;
import com.graccasoft.redkokia.model.entity.Treatment;
import com.graccasoft.redkokia.model.enums.BookingStatus;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-12T19:04:51+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public Booking toEntity(BookingDto dto) {
        if ( dto == null ) {
            return null;
        }

        Booking booking = new Booking();

        booking.setId( dto.id() );
        booking.setCreatedAt( dto.createdAt() );
        booking.setBookingDate( dto.bookingDate() );
        booking.setDuration( dto.duration() );
        booking.setStatus( dto.status() );
        booking.setClient( clientDtoToClient( dto.client() ) );
        booking.setTreatment( treatmentDtoToTreatment( dto.treatment() ) );

        return booking;
    }

    @Override
    public BookingDto toDto(Booking entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        Date createdAt = null;
        Date bookingDate = null;
        Integer duration = null;
        BookingStatus status = null;
        ClientDto client = null;
        TreatmentDto treatment = null;

        id = entity.getId();
        createdAt = entity.getCreatedAt();
        bookingDate = entity.getBookingDate();
        duration = entity.getDuration();
        status = entity.getStatus();
        client = clientToClientDto( entity.getClient() );
        treatment = treatmentToTreatmentDto( entity.getTreatment() );

        BookingDto bookingDto = new BookingDto( id, createdAt, bookingDate, duration, status, client, treatment );

        return bookingDto;
    }

    @Override
    public void updateEntity(BookingDto dto, Booking entity) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.id() );
        entity.setCreatedAt( dto.createdAt() );
        entity.setBookingDate( dto.bookingDate() );
        entity.setDuration( dto.duration() );
        entity.setStatus( dto.status() );
        if ( dto.client() != null ) {
            if ( entity.getClient() == null ) {
                entity.setClient( new Client() );
            }
            clientDtoToClient1( dto.client(), entity.getClient() );
        }
        else {
            entity.setClient( null );
        }
        if ( dto.treatment() != null ) {
            if ( entity.getTreatment() == null ) {
                entity.setTreatment( new Treatment() );
            }
            treatmentDtoToTreatment1( dto.treatment(), entity.getTreatment() );
        }
        else {
            entity.setTreatment( null );
        }
    }

    @Override
    public List<BookingDto> toDtoList(List<Booking> entities) {
        if ( entities == null ) {
            return null;
        }

        List<BookingDto> list = new ArrayList<BookingDto>( entities.size() );
        for ( Booking booking : entities ) {
            list.add( toDto( booking ) );
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

    protected Client clientDtoToClient(ClientDto clientDto) {
        if ( clientDto == null ) {
            return null;
        }

        Client client = new Client();

        client.setId( clientDto.id() );
        client.setFirstName( clientDto.firstName() );
        client.setSurname( clientDto.surname() );
        client.setPhoneNumber( clientDto.phoneNumber() );
        client.setEmail( clientDto.email() );
        client.setAddress( clientDto.address() );
        client.setCity( clientDto.city() );
        client.setCountry( clientDto.country() );
        client.setTenant( tenantDtoToTenant( clientDto.tenant() ) );

        return client;
    }

    protected Treatment treatmentDtoToTreatment(TreatmentDto treatmentDto) {
        if ( treatmentDto == null ) {
            return null;
        }

        Treatment treatment = new Treatment();

        treatment.setId( treatmentDto.id() );
        treatment.setName( treatmentDto.name() );
        treatment.setDescription( treatmentDto.description() );
        treatment.setPrice( treatmentDto.price() );
        treatment.setMinimumDuration( treatmentDto.minimumDuration() );
        treatment.setMaximumDuration( treatmentDto.maximumDuration() );
        treatment.setTenant( tenantDtoToTenant( treatmentDto.tenant() ) );

        return treatment;
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

    protected ClientDto clientToClientDto(Client client) {
        if ( client == null ) {
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

        id = client.getId();
        firstName = client.getFirstName();
        surname = client.getSurname();
        phoneNumber = client.getPhoneNumber();
        email = client.getEmail();
        address = client.getAddress();
        city = client.getCity();
        country = client.getCountry();
        tenant = tenantToTenantDto( client.getTenant() );

        ClientDto clientDto = new ClientDto( id, firstName, surname, phoneNumber, email, address, city, country, tenant );

        return clientDto;
    }

    protected TreatmentDto treatmentToTreatmentDto(Treatment treatment) {
        if ( treatment == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;
        BigDecimal price = null;
        Integer minimumDuration = null;
        Integer maximumDuration = null;
        TenantDto tenant = null;

        id = treatment.getId();
        name = treatment.getName();
        description = treatment.getDescription();
        price = treatment.getPrice();
        minimumDuration = treatment.getMinimumDuration();
        maximumDuration = treatment.getMaximumDuration();
        tenant = tenantToTenantDto( treatment.getTenant() );

        TreatmentDto treatmentDto = new TreatmentDto( id, name, description, price, minimumDuration, maximumDuration, tenant );

        return treatmentDto;
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

    protected void clientDtoToClient1(ClientDto clientDto, Client mappingTarget) {
        if ( clientDto == null ) {
            return;
        }

        mappingTarget.setId( clientDto.id() );
        mappingTarget.setFirstName( clientDto.firstName() );
        mappingTarget.setSurname( clientDto.surname() );
        mappingTarget.setPhoneNumber( clientDto.phoneNumber() );
        mappingTarget.setEmail( clientDto.email() );
        mappingTarget.setAddress( clientDto.address() );
        mappingTarget.setCity( clientDto.city() );
        mappingTarget.setCountry( clientDto.country() );
        if ( clientDto.tenant() != null ) {
            if ( mappingTarget.getTenant() == null ) {
                mappingTarget.setTenant( new Tenant() );
            }
            tenantDtoToTenant1( clientDto.tenant(), mappingTarget.getTenant() );
        }
        else {
            mappingTarget.setTenant( null );
        }
    }

    protected void treatmentDtoToTreatment1(TreatmentDto treatmentDto, Treatment mappingTarget) {
        if ( treatmentDto == null ) {
            return;
        }

        mappingTarget.setId( treatmentDto.id() );
        mappingTarget.setName( treatmentDto.name() );
        mappingTarget.setDescription( treatmentDto.description() );
        mappingTarget.setPrice( treatmentDto.price() );
        mappingTarget.setMinimumDuration( treatmentDto.minimumDuration() );
        mappingTarget.setMaximumDuration( treatmentDto.maximumDuration() );
        if ( treatmentDto.tenant() != null ) {
            if ( mappingTarget.getTenant() == null ) {
                mappingTarget.setTenant( new Tenant() );
            }
            tenantDtoToTenant1( treatmentDto.tenant(), mappingTarget.getTenant() );
        }
        else {
            mappingTarget.setTenant( null );
        }
    }
}
