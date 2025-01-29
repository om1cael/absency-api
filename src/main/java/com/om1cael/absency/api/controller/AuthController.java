package com.om1cael.absency.api.controller;

import com.om1cael.absency.api.exception.EntityFoundException;
import com.om1cael.absency.api.model.User;
import com.om1cael.absency.api.security.JwtManager;
import com.om1cael.absency.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    private ResponseEntity<User> register(@RequestBody @Valid User user) throws EntityFoundException {
        return new ResponseEntity<>(this.userService.register(user), HttpStatus.CREATED);
    }

}
