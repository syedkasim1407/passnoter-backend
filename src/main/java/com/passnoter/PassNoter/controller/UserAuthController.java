package com.passnoter.PassNoter.controller;

import com.passnoter.PassNoter.exception.UserNotFoundException;
import com.passnoter.PassNoter.model.UserAuth;
import com.passnoter.PassNoter.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class UserAuthController {
    @Autowired
    UserAuthRepository userAuthRepository;

    @PostMapping("/userAuth")
    public UserAuth create(@RequestBody UserAuth userAuth) {

        Optional<UserAuth> existingUser =
                userAuthRepository.findByEmail(userAuth.getEmail());

        if (existingUser.isPresent()) {
            return existingUser.get();

        }

        return userAuthRepository.save(userAuth);
    }
    @PostMapping("/login")
    public UserAuth login(@RequestBody UserAuth userAuth) {
        return userAuthRepository
                .findByEmailAndPassword(
                        userAuth.getEmail(),
                        userAuth.getPassword()
                )
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
    }


    @GetMapping("/getAllUsers")
    public List<UserAuth> getUsers()
    {
        return userAuthRepository.findAll();
    }

}
