package com.om1cael.absency.api.dto;

import jakarta.validation.constraints.NotNull;

public record UserLoginDTO(@NotNull String username,
                           @NotNull String password) {
}
