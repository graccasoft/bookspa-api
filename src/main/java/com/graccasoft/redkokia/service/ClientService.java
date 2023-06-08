package com.graccasoft.redkokia.service;

import com.graccasoft.redkokia.model.dto.ClientDto;
import com.graccasoft.redkokia.model.entity.Client;
import com.graccasoft.redkokia.model.mapper.ClientMapper;
import com.graccasoft.redkokia.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService  {

    private ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    public ClientDto saveClient(ClientDto clientDto){
        Client savedClient = clientRepository.save( clientMapper.toEntity(clientDto) );
        return clientMapper.toDto( savedClient );
    }

    public Client findByEmail(String email){
        return  clientRepository.findByEmail(email).orElse(null) ;
    }

    public List<ClientDto> getTenantClients(Long tenantId){
        return clientMapper.toDtoList( clientRepository.findAllByTenant_Id(tenantId) );
    }

}
