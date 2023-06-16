package com.graccasoft.redkokia.controller;

import com.graccasoft.redkokia.model.dto.GenericResponse;
import com.graccasoft.redkokia.model.dto.RegisterUserDto;
import com.graccasoft.redkokia.model.dto.TenantDto;
import com.graccasoft.redkokia.service.TenantService;
import com.graccasoft.redkokia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tenants")
public class TenantController {

    private final TenantService tenantService;
    private final UserService userService;
    @PostMapping
    public TenantDto saveTenant(@RequestBody TenantDto tenantDto){
        return tenantService.saveTenant( tenantDto );
    }

    @GetMapping
    public List<TenantDto> getTenants(){
        return tenantService.getTenants();
    }

    @GetMapping("{tenantId}")
    public TenantDto getTenant(@PathVariable Long tenantId){
        return tenantService.getTenant(tenantId);
    }
    @GetMapping("{tenantId}/users")
    public List<RegisterUserDto> getUsers(@PathVariable Long tenantId){
        return userService.getTenantUsers(tenantId);
    }

    @PostMapping("{tenantId}/users")
    public ResponseEntity<GenericResponse> saveUsers(@RequestBody RegisterUserDto registerUserDto){
        userService.saveUser(registerUserDto);
        return new ResponseEntity<>(
                new GenericResponse(true, "User Saved"),
                HttpStatus.CREATED);
    }

}
