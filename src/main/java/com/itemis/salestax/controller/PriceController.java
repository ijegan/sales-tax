package com.itemis.salestax.controller;

import com.itemis.salestax.dto.PriceDto;
import com.itemis.salestax.model.Product;
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
    private ProductService productService;


    @PostMapping("/compute")
    public ResponseEntity<List<PriceDto>>  calculatePrice(@RequestBody List<PriceDto> productList) {

        double output = 0;
        double outputTaxes =0;

        for (PriceDto priceDto : productList) {

            Double salesTax, importTax, taxes, total;

            Product product = productService.getProductByName(priceDto.getName());

            salesTax = product.getPrice() * (product.getSalesTax().getTaxValue() / 100);
            importTax = product.getPrice() * (product.getImportDuty().getDutyValue() / 100);

            taxes = salesTax + importTax;
            total = taxes + product.getPrice();

            priceDto.setTaxes(taxes);
            priceDto.setTotal(total);

            output = output + total;
            outputTaxes= outputTaxes+ taxes;

        }
        return new ResponseEntity<List<PriceDto>>(productList, HttpStatus.OK);
    }
}
