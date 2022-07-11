package com.itemis.salestax.controller;

import com.itemis.salestax.common.PriceResponse;
import com.itemis.salestax.dto.PriceDto;
import com.itemis.salestax.service.PriceCalculatorService;
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
    public ResponseEntity<PriceResponse> calculatePrice(@RequestBody List<PriceDto> productList) {

        PriceResponse body = priceCalculatorService.calculatePrice(productList);
        return new ResponseEntity<PriceResponse>(body, HttpStatus.OK);
    }
}
