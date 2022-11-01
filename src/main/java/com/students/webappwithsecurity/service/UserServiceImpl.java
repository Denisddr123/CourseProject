package com.students.webappwithsecurity.service;

import com.students.webappwithsecurity.dto.UserDto;
import com.students.webappwithsecurity.entity.Role;
import com.students.webappwithsecurity.entity.User;
import com.students.webappwithsecurity.repository.RoleRepository;
import com.students.webappwithsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository repository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository repository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName()+" "+userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = repository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> list = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = mapToUserDto(user);
            list.add(userDto);
        }
        return list;
    }
    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        String[] strings = user.getName().split(" ");
        userDto.setFirstName(strings[0]);
        userDto.setLastName(strings[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }
    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return repository.save(role);
    }
}
