package com.itemis.salestax.service;

import com.itemis.salestax.dto.PriceDto;
import com.itemis.salestax.model.Product;
import com.itemis.salestax.model.SalesTax;
import com.itemis.salestax.repository.SalesTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PriceCalculatorService {

    @Autowired
    private ProductService productService;

    public List<PriceDto> calculatePrice(List<PriceDto> productList) {
        double output = 0;
        double outputTaxes = 0;

        for (PriceDto priceDto : productList) {

            Double salesTax, importTax, taxes, total;

            Product product = productService.getProductByNameAndPrice(priceDto.getName(),priceDto.getPrice());

            salesTax = product.getPrice() * (product.getSalesTax().getTaxValue() / 100);
            importTax = product.getPrice() * (product.getImportDuty().getDutyValue() / 100);

            taxes = salesTax + importTax;
            total = taxes + product.getPrice();

            priceDto.setTaxes(taxes);
            priceDto.setTotal(total);

            output = output + total;
            outputTaxes = outputTaxes + taxes;

        }

        return productList;
    }

}
