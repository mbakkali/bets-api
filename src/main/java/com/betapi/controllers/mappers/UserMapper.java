package com.betapi.controllers.mappers;

import com.betapi.controllers.dtos.UserDTO;
import com.betapi.models.User;
import org.springframework.beans.BeanUtils;

public class UserMapper {

    public static User toUser(UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

    public static UserDTO toDto(User user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }
}
