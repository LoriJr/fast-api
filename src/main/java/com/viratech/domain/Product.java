package com.viratech.domain;

import com.viratech.dto.ProductUpdateRequest;
import com.viratech.enums.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer stock;
    private LocalDateTime createdAt;
    private boolean active;

    @PrePersist
    void prePersist(){
        if(createdAt == null) createdAt = LocalDateTime.now();
    }
}
