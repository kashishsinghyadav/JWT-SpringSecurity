package org.kashish.jwtspringsecurity.controller;


import org.kashish.jwtspringsecurity.model.Users;
import org.kashish.jwtspringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return userService.register(user);

    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        System.out.println(user);
        userService.verify(user);
        return "Login";
    }
}
