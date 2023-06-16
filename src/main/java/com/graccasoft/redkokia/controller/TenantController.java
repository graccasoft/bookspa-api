package com.graccasoft.redkokia.controller;

import com.graccasoft.redkokia.model.dto.TenantDto;
import com.graccasoft.redkokia.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tenants")
public class TenantController {

    private final TenantService tenantService;

    @PostMapping
    public TenantDto saveTenant(@RequestBody TenantDto tenantDto){
        return tenantService.saveTenant( tenantDto );
    }

    @GetMapping
    public List<TenantDto> getTenants(){
        return tenantService.getTenants();
    }

}
