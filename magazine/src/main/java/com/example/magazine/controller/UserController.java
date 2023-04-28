package com.example.magazine.controller;

import com.example.magazine.Iservice.IUser;
import com.example.magazine.model.Role;
import com.example.magazine.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class UserController {
    private final IUser iUser;

    public UserController(IUser iUser) {
        this.iUser = iUser;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", iUser.getAllUsers());
        return "users";
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", iUser.getUserById(id));
        return "edit_user";
    }

    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user) {
        User existingUser = iUser.getUserById(id);
        existingUser.setId(id);
        existingUser.setFirstName(existingUser.getFirstName());
        existingUser.setLastName(existingUser.getLastName());
        existingUser.setEmail(existingUser.getEmail());
        existingUser.setPassword(existingUser.getPassword());
        Collection<Role> existingRoles = existingUser.getRoles();
        existingRoles.clear();
        existingRoles.add(new Role(user.rolesAsString));
        existingUser.setRoles(existingRoles);

        iUser.updateUser(existingUser);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        iUser.deleteUserById(id);
        return "redirect:/users";
    }
}
