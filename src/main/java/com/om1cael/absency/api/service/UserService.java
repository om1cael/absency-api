package com.om1cael.absency.api.service;

import com.om1cael.absency.api.dto.JwtTokenDTO;
import com.om1cael.absency.api.dto.UserLoginDTO;
import com.om1cael.absency.api.exception.EntityFoundException;
import com.om1cael.absency.api.model.User;
import com.om1cael.absency.api.repository.UserRepository;
import com.om1cael.absency.api.security.JwtManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public JwtTokenDTO register(User userModel) throws EntityFoundException {
        Optional<User> repositoryUser = this.userRepository.findByUsername(userModel.getUsername());
        if(repositoryUser.isPresent()) {
            throw new EntityFoundException("User already exists");
        }

        userModel.setAbsentDays(0);
        userModel.setPassword(this.passwordEncoder.encode(userModel.getPassword()));
        this.userRepository.save(userModel);

        String token = this.jwtManager.createToken(userModel.getUsername());
        return new JwtTokenDTO(token);
    }

    public JwtTokenDTO login(UserLoginDTO userLoginDTO) {
        User user = this.userRepository.findByUsername(userLoginDTO.username())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if(!this.passwordEncoder.matches(userLoginDTO.password(), user.getPassword())) {
            throw new BadCredentialsException("An error occurred during the login");
        }

        String token = this.jwtManager.createToken(userLoginDTO.username());
        return new JwtTokenDTO(token);
    }
}
