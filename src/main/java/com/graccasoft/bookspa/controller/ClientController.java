package com.graccasoft.bookspa.controller;

import com.graccasoft.bookspa.helper.CSVHelper;
import com.graccasoft.bookspa.model.dto.ClientDto;
import com.graccasoft.bookspa.model.dto.GenericResponse;
import com.graccasoft.bookspa.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("clients-csv")
    public ResponseEntity<Resource> getFile(@RequestParam Long tenantId) {
        String filename = "clients.csv";
        List<ClientDto> clients= clientService.getTenantClients(tenantId);
        InputStreamResource file = new InputStreamResource(CSVHelper.clientsToCsv (clients));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @DeleteMapping("{clientId}")
    public GenericResponse deleteClient(@PathVariable Long clientId){
        clientService.deleteClient(clientId);
        return new GenericResponse(true, "Client deleted");
    }

}
