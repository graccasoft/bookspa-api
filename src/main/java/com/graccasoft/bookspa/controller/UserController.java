package com.graccasoft.bookspa.controller;

import com.graccasoft.bookspa.model.dto.GenericResponse;
import com.graccasoft.bookspa.model.dto.RegisterUserDto;
import com.graccasoft.bookspa.service.UserService;
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
    public ResponseEntity<GenericResponse> addNewUser(@RequestBody RegisterUserDto registerUserDto){

        userService.saveUser(registerUserDto);
        return new ResponseEntity<>(
                new GenericResponse(true, "User Saved"),
                HttpStatus.CREATED);
    }
}
