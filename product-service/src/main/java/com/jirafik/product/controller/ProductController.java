package com.jirafik.product.controller;

import com.jirafik.product.dto.ProductRequest;
import com.jirafik.product.dto.ProductResponse;
import com.jirafik.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest request){
        return productService.createProduct(request);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getProducts();
    }

}






















