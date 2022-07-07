package com.itemis.salestax.controller;

import com.itemis.salestax.common.Response;
import com.itemis.salestax.dto.ProductDto;
import com.itemis.salestax.model.Category;
import com.itemis.salestax.model.SalesTax;
import com.itemis.salestax.service.CategoryService;
import com.itemis.salestax.service.ProductService;
import com.itemis.salestax.service.SalesTaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SalesTaxService salesTaxService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> body = productService.listProducts();
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        Optional<SalesTax> optionalSalesTax = salesTaxService.readSalesTax(productDto.getSalesTaxId());

        if (optionalCategory.isEmpty()) {
            return new ResponseEntity<>(new Response(false, "category is invalid"), HttpStatus.CONFLICT);
        }

        if (optionalSalesTax.isEmpty()) {
            return new ResponseEntity<>(new Response(false, "SalesTax is invalid"), HttpStatus.CONFLICT);
        }

        Category category = optionalCategory.get();
        SalesTax salesTax = optionalSalesTax.get();
        productService.addProduct(productDto, category, salesTax);

        return new ResponseEntity<>(new Response(true, "Product has been added"), HttpStatus.CREATED);
    }

    @PostMapping("/update/{productID}")
    public ResponseEntity<Response> updateProduct(@PathVariable("productID") Integer productID, @RequestBody @Valid ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        Optional<SalesTax> optionalSalesTax = salesTaxService.readSalesTax(productDto.getSalesTaxId());

        if (optionalCategory.isEmpty()) {
            return new ResponseEntity<Response>(new Response(false, "category is invalid"), HttpStatus.CONFLICT);
        }

        if (optionalSalesTax.isEmpty()) {
            return new ResponseEntity<>(new Response(false, "SalesTax is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        SalesTax salesTax = optionalSalesTax.get();
        productService.updateProduct(productID, productDto, category, salesTax);
        return new ResponseEntity<Response>(new Response(true, "Product has been updated"), HttpStatus.OK);
    }
}