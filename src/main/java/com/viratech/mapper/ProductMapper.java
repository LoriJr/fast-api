package com.viratech.mapper;

import com.viratech.domain.Product;
import com.viratech.dto.ProductRequest;
import com.viratech.dto.ProductResponse;
import com.viratech.dto.ProductUpdateRequest;
import com.viratech.enums.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public ProductResponse toDto(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().toApi(),
                product.getStock(),
                product.getCreatedAt(),
                product.isActive()
        );
    }

    public Product toEntity(ProductRequest request){
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .category(Category.fromApi(request.category()))
                .stock(request.stock())
                .build();
    }

    public void updateEntityFromDto(ProductUpdateRequest request, Product product) {
        if (request.name() != null) product.setName(request.name());
        if (request.description() != null) product.setDescription(request.description());
        if (request.price() != null) product.setPrice(request.price());
        if (request.category() != null) product.setCategory(Category.fromApi(request.category()));
        if (request.stock() != null) product.setStock(request.stock());
        if (request.active() != null) product.setActive(request.active());
    }

    public List<ProductResponse> toList(List<Product> productList){
        return productList.stream()
                .map(product -> toDto(product))
                .toList();
    }
}
