package com.viratech.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        String category,
        Integer stock,
        LocalDateTime createdAt,
        boolean active
) {
}
