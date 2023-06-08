package com.graccasoft.redkokia.controller;

import com.graccasoft.redkokia.model.dto.ClientDto;
import com.graccasoft.redkokia.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("clients")
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ClientDto saveClient(@RequestBody ClientDto clientDto){
        return clientService.saveClient(clientDto);
    }

    @GetMapping
    public List<ClientDto> getAllByTenant(@RequestParam Long tenantId){
        return clientService.getTenantClients(tenantId);
    }

}
