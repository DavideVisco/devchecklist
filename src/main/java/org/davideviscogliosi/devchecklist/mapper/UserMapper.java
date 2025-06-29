package org.davideviscogliosi.devchecklist.mapper;

import org.davideviscogliosi.devchecklist.dto.UserRegistrationDTO;
import org.davideviscogliosi.devchecklist.model.User;

public class UserMapper {

    public static User toEntity(UserRegistrationDTO dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
}
