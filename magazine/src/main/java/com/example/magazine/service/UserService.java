package com.example.magazine.service;

import com.example.magazine.Iservice.IUser;
import com.example.magazine.dto.UserDTO;
import com.example.magazine.model.Role;
import com.example.magazine.model.User;
import com.example.magazine.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUser {

    private final UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo) {
        super();
        this.userRepo = userRepo;
    }


    @Override
    public User save(UserDTO userDTO) {
        User user = new User(userDTO.getFirstName(),
                userDTO.getLastName(), userDTO.getEmail(),
                passwordEncoder.encode(userDTO.getPassword()), Arrays.asList(new Role("ROLE_USER")));

        return userRepo.save(user);
    }


    @PostConstruct
    public void createAdminUser() {
        User admin = new User("admin", "admin", "admin@example.com",
                passwordEncoder.encode("password"), Arrays.asList(new Role("ROLE_ADMIN")));
        userRepo.save(admin);
    }

    @PostConstruct
    public void createDefaultUser() {
        User default_user = new User("default", "user", "user@example.com",
                passwordEncoder.encode("password"), Arrays.asList(new Role("ROLE_USER")));
        userRepo.save(default_user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id){
        return userRepo.findById(id).get();
    }

    @Override
    public User updateUser(User user){
        return userRepo.save(user);
    }

    @Override
    public void deleteUserById(Long id){
        userRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepo.findByEmail(username);
        if(user == null) {
            throw new RuntimeException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
