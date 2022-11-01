package com.students.webappwithsecurity.service;

import com.students.webappwithsecurity.dto.UserDto;
import com.students.webappwithsecurity.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
