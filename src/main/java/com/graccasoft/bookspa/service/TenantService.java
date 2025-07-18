package com.graccasoft.bookspa.service;

import com.graccasoft.bookspa.exception.RecordNotFoundException;
import com.graccasoft.bookspa.model.dto.TenantDto;
import com.graccasoft.bookspa.model.entity.Tenant;
import com.graccasoft.bookspa.model.mapper.TenantMapper;
import com.graccasoft.bookspa.repository.TenantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;

    public TenantDto saveTenant(TenantDto tenantDto){
        Tenant tenant = tenantMapper.toEntity(tenantDto);
        if( tenant.getReference() == null || tenant.getReference().isEmpty() ){
            tenant.setReference( generateTenantReference() );
        }
        return tenantMapper.toDto( tenantRepository.save( tenant  )  );
    }


    public List<TenantDto> getTenants(){
        return tenantMapper.toDtoList( tenantRepository.findAllByIsDeleted(false) );
    }

    public TenantDto getTenant(Long tenantId){
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(()->new EntityNotFoundException("Tenant not found"));

        return tenantMapper.toDto(tenant);
    }
    public TenantDto findByReference(String reference){
        Tenant tenant = tenantRepository.findByReference(reference)
                .orElseThrow(()->new EntityNotFoundException("Tenant with provided reference not found"));

        return tenantMapper.toDto(tenant);
    }

    //todo add this to a utility or helper class
    private String generateTenantReference(){
        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(candidateChars.length());
            sb.append(candidateChars.charAt(index));
        }
        return sb.toString().toLowerCase() ;
    }

    public void toggleActive(Long tenantId){
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(()-> new RecordNotFoundException("Tenant not found"));

        var status = tenant.getIsActive() == null || !tenant.getIsActive();
        tenant.setIsActive ( status );
        tenantRepository.save(tenant);
    }

    public void deleteTenant(Long tenantId){
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(()-> new RecordNotFoundException("Tenant not found"));

        tenant.setIsDeleted(true);
        tenant.setIsActive (false);
        tenantRepository.save(tenant);
    }

}
