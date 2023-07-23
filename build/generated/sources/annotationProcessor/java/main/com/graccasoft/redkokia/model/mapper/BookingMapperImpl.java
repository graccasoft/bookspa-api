package com.graccasoft.redkokia.model.mapper;

import com.graccasoft.redkokia.model.dto.BookingDto;
import com.graccasoft.redkokia.model.dto.ClientDto;
import com.graccasoft.redkokia.model.dto.EmployeeDto;
import com.graccasoft.redkokia.model.dto.TenantDto;
import com.graccasoft.redkokia.model.dto.TreatmentCategoryDto;
import com.graccasoft.redkokia.model.dto.TreatmentDto;
import com.graccasoft.redkokia.model.entity.Booking;
import com.graccasoft.redkokia.model.entity.Client;
import com.graccasoft.redkokia.model.entity.Employee;
import com.graccasoft.redkokia.model.entity.Tenant;
import com.graccasoft.redkokia.model.entity.Treatment;
import com.graccasoft.redkokia.model.entity.TreatmentCategory;
import com.graccasoft.redkokia.model.enums.BookingStatus;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-10T19:10:27+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.7 (Private Build)"
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
        booking.setTreatments( treatmentDtoListToTreatmentList( dto.treatments() ) );
        booking.setClientNotes( dto.clientNotes() );
        booking.setReference( dto.reference() );
        booking.setEmployee( employeeDtoToEmployee( dto.employee() ) );
        booking.setPaymentMethod( dto.paymentMethod() );

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
        List<TreatmentDto> treatments = null;
        String clientNotes = null;
        String reference = null;
        EmployeeDto employee = null;
        String paymentMethod = null;

        id = entity.getId();
        createdAt = entity.getCreatedAt();
        bookingDate = entity.getBookingDate();
        duration = entity.getDuration();
        status = entity.getStatus();
        client = clientToClientDto( entity.getClient() );
        treatments = treatmentListToTreatmentDtoList( entity.getTreatments() );
        clientNotes = entity.getClientNotes();
        reference = entity.getReference();
        employee = employeeToEmployeeDto( entity.getEmployee() );
        paymentMethod = entity.getPaymentMethod();

        BookingDto bookingDto = new BookingDto( id, createdAt, bookingDate, duration, status, client, treatments, clientNotes, reference, employee, paymentMethod );

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
        if ( entity.getTreatments() != null ) {
            List<Treatment> list = treatmentDtoListToTreatmentList( dto.treatments() );
            if ( list != null ) {
                entity.getTreatments().clear();
                entity.getTreatments().addAll( list );
            }
            else {
                entity.setTreatments( null );
            }
        }
        else {
            List<Treatment> list = treatmentDtoListToTreatmentList( dto.treatments() );
            if ( list != null ) {
                entity.setTreatments( list );
            }
        }
        entity.setClientNotes( dto.clientNotes() );
        entity.setReference( dto.reference() );
        if ( dto.employee() != null ) {
            if ( entity.getEmployee() == null ) {
                entity.setEmployee( new Employee() );
            }
            employeeDtoToEmployee1( dto.employee(), entity.getEmployee() );
        }
        else {
            entity.setEmployee( null );
        }
        entity.setPaymentMethod( dto.paymentMethod() );
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
        tenant.setReference( tenantDto.reference() );
        tenant.setOpeningTime( tenantDto.openingTime() );
        tenant.setClosingTime( tenantDto.closingTime() );

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

    protected TreatmentCategory treatmentCategoryDtoToTreatmentCategory(TreatmentCategoryDto treatmentCategoryDto) {
        if ( treatmentCategoryDto == null ) {
            return null;
        }

        TreatmentCategory treatmentCategory = new TreatmentCategory();

        treatmentCategory.setId( treatmentCategoryDto.id() );
        treatmentCategory.setName( treatmentCategoryDto.name() );

        return treatmentCategory;
    }

    protected Treatment treatmentDtoToTreatment(TreatmentDto treatmentDto) {
        if ( treatmentDto == null ) {
            return null;
        }

        Treatment treatment = new Treatment();

        treatment.setId( treatmentDto.id() );
        treatment.setCategory( treatmentCategoryDtoToTreatmentCategory( treatmentDto.category() ) );
        treatment.setName( treatmentDto.name() );
        treatment.setDescription( treatmentDto.description() );
        treatment.setPrice( treatmentDto.price() );
        treatment.setMinimumDuration( treatmentDto.minimumDuration() );
        treatment.setMaximumDuration( treatmentDto.maximumDuration() );
        treatment.setTenant( tenantDtoToTenant( treatmentDto.tenant() ) );
        treatment.setIsPromotion( treatmentDto.isPromotion() );

        return treatment;
    }

    protected List<Treatment> treatmentDtoListToTreatmentList(List<TreatmentDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Treatment> list1 = new ArrayList<Treatment>( list.size() );
        for ( TreatmentDto treatmentDto : list ) {
            list1.add( treatmentDtoToTreatment( treatmentDto ) );
        }

        return list1;
    }

    protected Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeDto.id() );
        employee.setFirstName( employeeDto.firstName() );
        employee.setSurname( employeeDto.surname() );
        employee.setPhoneNumber( employeeDto.phoneNumber() );
        employee.setIsAvailable( employeeDto.isAvailable() );
        employee.setTenant( tenantDtoToTenant( employeeDto.tenant() ) );

        return employee;
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
        String reference = null;
        String openingTime = null;
        String closingTime = null;

        id = tenant.getId();
        companyName = tenant.getCompanyName();
        companyPhone = tenant.getCompanyPhone();
        companyEmail = tenant.getCompanyEmail();
        companyAddress = tenant.getCompanyAddress();
        contactName = tenant.getContactName();
        contactPhone = tenant.getContactPhone();
        reference = tenant.getReference();
        openingTime = tenant.getOpeningTime();
        closingTime = tenant.getClosingTime();

        TenantDto tenantDto = new TenantDto( id, companyName, companyPhone, companyEmail, companyAddress, contactName, contactPhone, reference, openingTime, closingTime );

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

    protected TreatmentCategoryDto treatmentCategoryToTreatmentCategoryDto(TreatmentCategory treatmentCategory) {
        if ( treatmentCategory == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = treatmentCategory.getId();
        name = treatmentCategory.getName();

        TreatmentCategoryDto treatmentCategoryDto = new TreatmentCategoryDto( id, name );

        return treatmentCategoryDto;
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
        TreatmentCategoryDto category = null;
        Boolean isPromotion = null;

        id = treatment.getId();
        name = treatment.getName();
        description = treatment.getDescription();
        price = treatment.getPrice();
        minimumDuration = treatment.getMinimumDuration();
        maximumDuration = treatment.getMaximumDuration();
        tenant = tenantToTenantDto( treatment.getTenant() );
        category = treatmentCategoryToTreatmentCategoryDto( treatment.getCategory() );
        isPromotion = treatment.getIsPromotion();

        TreatmentDto treatmentDto = new TreatmentDto( id, name, description, price, minimumDuration, maximumDuration, tenant, category, isPromotion );

        return treatmentDto;
    }

    protected List<TreatmentDto> treatmentListToTreatmentDtoList(List<Treatment> list) {
        if ( list == null ) {
            return null;
        }

        List<TreatmentDto> list1 = new ArrayList<TreatmentDto>( list.size() );
        for ( Treatment treatment : list ) {
            list1.add( treatmentToTreatmentDto( treatment ) );
        }

        return list1;
    }

    protected EmployeeDto employeeToEmployeeDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        Long id = null;
        String firstName = null;
        String surname = null;
        String phoneNumber = null;
        Boolean isAvailable = null;
        TenantDto tenant = null;

        id = employee.getId();
        firstName = employee.getFirstName();
        surname = employee.getSurname();
        phoneNumber = employee.getPhoneNumber();
        isAvailable = employee.getIsAvailable();
        tenant = tenantToTenantDto( employee.getTenant() );

        EmployeeDto employeeDto = new EmployeeDto( id, firstName, surname, phoneNumber, isAvailable, tenant );

        return employeeDto;
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
        mappingTarget.setReference( tenantDto.reference() );
        mappingTarget.setOpeningTime( tenantDto.openingTime() );
        mappingTarget.setClosingTime( tenantDto.closingTime() );
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

    protected void employeeDtoToEmployee1(EmployeeDto employeeDto, Employee mappingTarget) {
        if ( employeeDto == null ) {
            return;
        }

        mappingTarget.setId( employeeDto.id() );
        mappingTarget.setFirstName( employeeDto.firstName() );
        mappingTarget.setSurname( employeeDto.surname() );
        mappingTarget.setPhoneNumber( employeeDto.phoneNumber() );
        mappingTarget.setIsAvailable( employeeDto.isAvailable() );
        if ( employeeDto.tenant() != null ) {
            if ( mappingTarget.getTenant() == null ) {
                mappingTarget.setTenant( new Tenant() );
            }
            tenantDtoToTenant1( employeeDto.tenant(), mappingTarget.getTenant() );
        }
        else {
            mappingTarget.setTenant( null );
        }
    }
}
