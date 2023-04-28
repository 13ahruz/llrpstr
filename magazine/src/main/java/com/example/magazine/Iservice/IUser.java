package com.example.magazine.Iservice;

import com.example.magazine.dto.UserDTO;
import com.example.magazine.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface IUser extends UserDetailsService {
    User save(UserDTO userDTO);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(User user);

    void deleteUserById(Long id);
}
