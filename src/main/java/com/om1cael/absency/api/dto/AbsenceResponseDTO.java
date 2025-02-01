package com.om1cael.absency.api.dto;

public record AbsenceResponseDTO (int schoolDays,
                                  int maximumAbsenceInPercentage,
                                  int absentDays) {}