package com.viratech.controller;

import com.viratech.dto.ProductFilterRequest;
import com.viratech.dto.ProductRequest;
import com.viratech.dto.ProductResponse;
import com.viratech.dto.ProductUpdateRequest;
import com.viratech.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@Valid @RequestBody ProductRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAll(
            @ModelAttribute ProductFilterRequest filter,
                            Pageable pageable){
        return ResponseEntity.ok().body(service.findAllProducts(filter, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> find(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateRequest request
    ){
        return ResponseEntity.ok().body(service.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ProductResponse> deactivate(@PathVariable Long id){
        return ResponseEntity.ok().body(service.deactivateProduct(id));
    }

}
