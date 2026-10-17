package com.viratech.dto;

import java.math.BigDecimal;

public record ProductFilterRequest(
        String name,
        String category,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        Integer minStock
) {
}
