package org.kashish.jwtspringsecurity.service;


import org.kashish.jwtspringsecurity.model.Users;
import org.kashish.jwtspringsecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public Users register(Users user){
        return userRepo.save(user);
    }

}
