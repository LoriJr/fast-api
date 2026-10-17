package com.viratech.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductUpdateRequest(

        @Size(min = 3, max = 100)
        String name,

        @Size(max = 500)
        String description,

        @Positive
        BigDecimal price,

        String category,

        @PositiveOrZero
        Integer stock,

        Boolean active
) {
}
