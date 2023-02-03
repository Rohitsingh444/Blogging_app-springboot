package com.springBoot.blogApp.services;

import com.springBoot.blogApp.entities.User;
import com.springBoot.blogApp.payloads.UserDto;
import org.springframework.data.annotation.Id;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    List<UserDto> getAllUsers();
    UserDto getUserById(int userId);
    UserDto updateUserById(UserDto user,int userId);
    Void deleteUserById(int userId);
}
