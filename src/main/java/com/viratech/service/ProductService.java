package com.viratech.service;

import com.viratech.domain.Product;
import com.viratech.dto.ProductFilterRequest;
import com.viratech.dto.ProductRequest;
import com.viratech.dto.ProductResponse;
import com.viratech.dto.ProductUpdateRequest;
import com.viratech.exceptions.ResourceNotFoundException;
import com.viratech.mapper.ProductMapper;
import com.viratech.repository.ProductRepository;
import com.viratech.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Transactional
    public ProductResponse save(ProductRequest request){
        Product product = mapper.toEntity(request);
        product.setActive(true);

        return mapper.toDto(repository.save(product));
    }

    //findAll
    public Page<ProductResponse> findAllProducts(ProductFilterRequest filter, Pageable pageable){

        Specification<Product> spec = ProductSpecification.filter(filter);

        return repository.findAll(spec, pageable)
                .map(mapper::toDto);

    }

    //findById
    public ProductResponse findById(Long id){
        return mapper.toDto(getProduct(id));
    }

    //update
    @Transactional
    public ProductResponse updateProduct(Long id, ProductUpdateRequest request){
        Product product = getProduct(id);
        mapper.updateEntityFromDto(request, product);
        return mapper.toDto(repository.save(product));
    }

    //delete
    public void deleteProduct(Long id){
        Product product = getProduct(id);
        repository.delete(product);
    }

    @Transactional
    public ProductResponse deactivateProduct(Long id){
        Product product = getProduct(id);
        product.setActive(false);
        return mapper.toDto(product);
    }

    private Product getProduct(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
    }
}
