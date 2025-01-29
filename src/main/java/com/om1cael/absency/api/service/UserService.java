package com.om1cael.absency.api.service;

import com.om1cael.absency.api.exception.EntityFoundException;
import com.om1cael.absency.api.model.User;
import com.om1cael.absency.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User userModel) throws EntityFoundException {
        Optional<User> repositoryUser = this.userRepository.findByUsername(userModel.getUsername());
        if(repositoryUser.isPresent()) {
            throw new EntityFoundException("User already exists");
        }

        userModel.setAbsentDays(0);
        userModel.setPassword(this.passwordEncoder.encode(userModel.getPassword()));
        this.userRepository.save(userModel);

        return userModel;
    }

}
