package com.itemis.salestax.controller;

import com.itemis.salestax.common.Response;
import com.itemis.salestax.dto.ProductDto;
import com.itemis.salestax.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> body = productService.listProducts();
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addProduct(@RequestBody ProductDto productDto) {
        productService.validateProductDto(productDto);
        productService.addProduct(productDto);
        return new ResponseEntity<>(new Response(true, "Product has been added"), HttpStatus.CREATED);
    }

    @PostMapping("/update/{productID}")
    public ResponseEntity<Response> updateProduct(@PathVariable("productID") Integer productID, @RequestBody @Valid ProductDto productDto) {
        productService.validateProductDto(productDto);
        productService.updateProduct(productID,productDto);
        return new ResponseEntity<Response>(new Response(true, "Product has been updated"), HttpStatus.OK);
    }
}