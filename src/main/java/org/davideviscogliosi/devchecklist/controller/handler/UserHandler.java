package org.davideviscogliosi.devchecklist.controller.handler;

import org.davideviscogliosi.devchecklist.dto.OutcomeDTO;
import org.davideviscogliosi.devchecklist.dto.UserRegistrationDTO;
import org.davideviscogliosi.devchecklist.mapper.UserMapper;
import org.davideviscogliosi.devchecklist.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class UserHandler extends ResponseHandler {

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<OutcomeDTO<String>> createUser(UserRegistrationDTO userRegistrationDTO) {
        return executeWithResponse("create-user" , () -> userService.register(UserMapper.toEntity(userRegistrationDTO)));
    }

}
