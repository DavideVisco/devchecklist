package org.davideviscogliosi.devchecklist.controller;

import org.davideviscogliosi.devchecklist.controller.handler.UserHandler;
import org.davideviscogliosi.devchecklist.dto.OutcomeDTO;
import org.davideviscogliosi.devchecklist.dto.UserRegistrationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserHandler userHandler;

    public UserController(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @PostMapping
    public ResponseEntity<OutcomeDTO<String>> register(@RequestBody UserRegistrationDTO request) {
        return userHandler.createUser(request);
    }
}