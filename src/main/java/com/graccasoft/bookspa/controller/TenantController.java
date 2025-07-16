package com.graccasoft.bookspa.controller;

import com.graccasoft.bookspa.model.dto.GenericResponse;
import com.graccasoft.bookspa.model.dto.RegisterUserDto;
import com.graccasoft.bookspa.model.dto.TenantDto;
import com.graccasoft.bookspa.service.UserService;
import com.graccasoft.bookspa.service.TenantService;
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

    @PutMapping("{tenantId}/users/{userId}")
    public ResponseEntity<GenericResponse> updateUser(@RequestBody RegisterUserDto registerUserDto){
        userService.editUser(registerUserDto);
        return new ResponseEntity<>(
                new GenericResponse(true, "User Saved"),
                HttpStatus.CREATED);
    }

    @DeleteMapping("{tenantId}/users/{userId}")
    public ResponseEntity<GenericResponse> deleteUser(@PathVariable Long userId){
        userService.deleteUser (userId);
        return new ResponseEntity<>(
                new GenericResponse(true, "User has been deleted"),
                HttpStatus.CREATED);
    }
    @PatchMapping("{tenantId}")
    public GenericResponse toggleActive(@PathVariable Long tenantId){
        tenantService.toggleActive(tenantId);
        return new GenericResponse(true, "Tenant status updated");
    }

    @DeleteMapping("{tenantId}")
    public GenericResponse deleteTenant(@PathVariable Long tenantId){
        tenantService.deleteTenant(tenantId);
        return new GenericResponse(true, "Tenant status updated");
    }

}
