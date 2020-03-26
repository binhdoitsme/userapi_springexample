package com.example.userapi.controller;

import com.example.userapi.base.Composite;
import com.example.userapi.domain.dto.UserDto;
import com.example.userapi.domain.dto.UserToken;
import com.example.userapi.domain.usecase.LoginUseCase;
import com.example.userapi.domain.usecase.RegisterUserUseCase;
import com.example.userapi.domain.usecase.UpdateUserUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private @Autowired LoginUseCase loginUseCase;
    private @Autowired RegisterUserUseCase registerUseCase;
    private @Autowired UpdateUserUseCase updateUserUseCase;

    @GetMapping(value = "/register", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<UserToken> login(@RequestBody UserDto loginInformation) {
        UserToken userToken = loginUseCase.handle(loginInformation);
        if (userToken.getAuthToken().equals("'Unauthorized!'")) {
            return ResponseEntity.status(400).body(userToken);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(userToken);
        }
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> register(@RequestBody UserDto userInformation) {
        Object handledStatus = registerUseCase.handle(userInformation);
        return handledStatus instanceof Boolean ?
                new ResponseEntity<>(handledStatus, HttpStatus.OK)
                : new ResponseEntity<>(handledStatus, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/update-user", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> update(@RequestParam String oldUsername, @RequestBody UserDto newUserInformation) {
        Composite<UserDto, String> userChangeInformation = new Composite<>(newUserInformation, oldUsername);
        Object handledStatus = updateUserUseCase.handle(userChangeInformation);
        return handledStatus instanceof Boolean ?
                new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(handledStatus, HttpStatus.BAD_REQUEST);
    }
}
