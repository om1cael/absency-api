package com.om1cael.absency.api.service;

import com.om1cael.absency.api.exception.IllegalAbsenceStateException;
import com.om1cael.absency.api.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AbsenceService {

    @Autowired
    private UserService userService;

    public int increaseAbsence() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user == null) {
            throw new EntityNotFoundException("User not found");
        }

        user.setAbsentDays(user.getAbsentDays() + 1);
        this.userService.save(user);
        return user.getAbsentDays();
    }

    public int decreaseAbsence() throws IllegalAbsenceStateException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user == null) {
            throw new EntityNotFoundException("User not found");
        }

        if(user.getAbsentDays() <= 0) {
            throw new IllegalAbsenceStateException("Absences cannot be set to negative");
        }

        user.setAbsentDays(user.getAbsentDays() - 1);
        this.userService.save(user);
        return user.getAbsentDays();
    }
}
