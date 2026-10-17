package com.viratech.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "name is not blank")
        @Size(min = 3, max = 100)
        String name,

        @NotBlank(message = "description is not blank")
        @Size(max = 500)
        String description,

        @NotNull
        @Positive
        BigDecimal price,

        @NotBlank
        String category,

        @NotNull
        @PositiveOrZero
        Integer stock
) {
}
