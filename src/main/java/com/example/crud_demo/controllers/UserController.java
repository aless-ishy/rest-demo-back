package com.example.crud_demo.controllers;

import com.example.crud_demo.models.User;
import com.example.crud_demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/adduser")
    public User addUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }
}
