package com.example.airbnb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.airbnb.entities.User;
import com.example.airbnb.repositories.UserRepository;

/**
 * UserController
 */
@RestController
@RequestMapping(path="/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

   

    @GetMapping(value="/users", produces="application/json")
    public List<User> displayUsers(){
        return userRepository.getAllUsers();

    }

    @GetMapping(path="/users_emails", produces="application/json")
    public List<User> displayUsersByEmail(@RequestParam String email){
        return userRepository.getUsersByEmail(email);
    }

    @GetMapping(path="/create_user")
    public void addUser(){
        userRepository.addUser("Test FN", "Test LN", "test@test.com", "0123456789");
    }

    @GetMapping(path="/update_user")
    public void updateUser(){
        userRepository.updateUser("Test FN", "New FN");
    }


    @GetMapping(path="/delete_user")
    public void deleteUser(){
        userRepository.deleteUser(7);
    }

    // @PostMapping(value="/users", produces="application/json")
    // public String createUser(@RequestBody String payload) {
    //     System.out.println("");
    //     return "{\"name\": 123}";
    // }


    // @PostMapping(value="/users", produces="application/json")
    // public String createUser(@RequestBody User payload) {
    //     System.out.println("");
    //     return "{\"name\": 123}";
    // }

    @PostMapping(value="/users", produces="application/json")
    public User createUser(@RequestBody User user) {
            userRepository.createUser(user);
            return user;
    }


}