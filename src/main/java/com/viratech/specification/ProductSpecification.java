package com.viratech.specification;

import com.viratech.domain.Product;
import com.viratech.dto.ProductFilterRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> filter(ProductFilterRequest filter){

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (filter.name() != null && !filter.name().isBlank()) {
                predicates.add(
                        cb.like(cb.lower(root.get("name")), "%" + filter.name().toLowerCase() + "%")

                );
            }

            if (filter.category() != null && !filter.category().isBlank()) {
                predicates.add(
                        cb.like(cb.lower(root.get("category")), "%" + filter.category().toLowerCase() + "%")

                );
            }

            if (filter.minPrice() != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get("price"), filter.minPrice()));
            }

            if (filter.maxPrice() != null) {
                predicates.add(
                        cb.lessThanOrEqualTo(root.get("price"), filter.maxPrice()));
            }

            if (filter.minStock() != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get("stock"), filter.minStock()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

    }
}
