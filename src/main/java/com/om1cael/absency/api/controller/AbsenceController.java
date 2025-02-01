package com.om1cael.absency.api.controller;

import com.om1cael.absency.api.dto.AbsenceResponseDTO;
import com.om1cael.absency.api.dto.ResponseDTO;
import com.om1cael.absency.api.exception.IllegalAbsenceStateException;
import com.om1cael.absency.api.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/absences")
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;

    @PutMapping("/increase")
    private ResponseEntity<ResponseDTO> increaseAbsences() {
        int absences = this.absenceService.increaseAbsence();
        return new ResponseEntity<>(new ResponseDTO(true, String.valueOf(absences)), HttpStatus.OK);
    }

    @PutMapping("/decrease")
    private ResponseEntity<ResponseDTO> decreaseAbsences() throws IllegalAbsenceStateException {
        int absences = this.absenceService.decreaseAbsence();
        return new ResponseEntity<>(new ResponseDTO(true, String.valueOf(absences)), HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<AbsenceResponseDTO> getUserAbsenceDetails() {
        return new ResponseEntity<>(this.absenceService.getUserAbsenceDetails(), HttpStatus.OK);
    }
}
