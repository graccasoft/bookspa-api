package com.graccasoft.bookspa.service;

import com.graccasoft.bookspa.model.dto.ClientDto;
import com.graccasoft.bookspa.model.entity.Client;
import com.graccasoft.bookspa.model.mapper.ClientMapper;
import com.graccasoft.bookspa.exception.RecordNotFoundException;
import com.graccasoft.bookspa.repository.ClientRepository;
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
        return clientMapper.toDtoList( clientRepository.findAllByTenant_Id(tenantId).stream().filter(client -> !client.getIsDeleted()).toList() );
    }

    public void deleteClient(Long clientId){
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()-> new RecordNotFoundException("Client with ID not found"));

        client.setIsDeleted(true);
        clientRepository.save(client);
    }
}
