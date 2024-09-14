package com.nibm.taskTinker.mapper;

import com.nibm.taskTinker.dto.UserDTO;
import com.nibm.taskTinker.model.User;

import java.util.List;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getUserEmail(), user.getUserName(), user.getUserPosition());
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserEmail(userDTO.getUserEmail());
        user.setUserName(userDTO.getUserName());
        user.setUserPosition(userDTO.getUserPosition());
        return user;
    }

    public static List<UserDTO> toDTOList(List<User> users) {
        return users.stream().map(UserMapper::toDTO).toList();
    }
}
