package com.graccasoft.redkokia.controller;

import com.graccasoft.redkokia.model.dto.GenericResponse;
import com.graccasoft.redkokia.model.dto.UserDto;
import com.graccasoft.redkokia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<GenericResponse> addNewUser(@RequestBody UserDto userDto){

        userService.saveUser(userDto);
        return new ResponseEntity<>(
                new GenericResponse(true, "User Saved"),
                HttpStatus.CREATED);
    }
}
