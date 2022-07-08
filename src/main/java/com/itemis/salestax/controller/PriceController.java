package com.itemis.salestax.controller;

import com.itemis.salestax.dto.PriceDto;
import com.itemis.salestax.model.Product;
import com.itemis.salestax.service.PriceCalculatorService;
import com.itemis.salestax.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/price-calculator")

public class PriceController {

    @Autowired
    private PriceCalculatorService priceCalculatorService;

    @PostMapping("/compute")
    public ResponseEntity<List<PriceDto>>  calculatePrice(@RequestBody List<PriceDto> productList) {

        priceCalculatorService.calculatePrice(productList);

        return new ResponseEntity<List<PriceDto>>(productList, HttpStatus.OK);
    }
}
