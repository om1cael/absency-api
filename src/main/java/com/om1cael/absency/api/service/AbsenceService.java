package com.om1cael.absency.api.service;

import com.om1cael.absency.api.dto.AbsenceResponseDTO;
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
        User user = this.getUser();

        user.setAbsentDays(user.getAbsentDays() + 1);
        this.userService.save(user);
        return user.getAbsentDays();
    }

    public int decreaseAbsence() throws IllegalAbsenceStateException {
        User user = this.getUser();

        if(user.getAbsentDays() <= 0) {
            throw new IllegalAbsenceStateException("Absences cannot be set to negative");
        }

        user.setAbsentDays(user.getAbsentDays() - 1);
        this.userService.save(user);
        return user.getAbsentDays();
    }

    public AbsenceResponseDTO getUserAbsenceDetails() {
        User user = this.getUser();

        return new AbsenceResponseDTO(user.getSchoolDays(), user.getMaximumAbsenceInPercentage(), user.getAbsentDays());
    }

    private User getUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user == null) {
            throw new EntityNotFoundException("User not found");
        }
        return user;
    }
}
