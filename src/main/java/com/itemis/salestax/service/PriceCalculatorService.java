package com.itemis.salestax.service;

import com.itemis.salestax.common.PriceResponse;
import com.itemis.salestax.dto.PriceDto;
import com.itemis.salestax.exceptions.CustomException;
import com.itemis.salestax.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
public class PriceCalculatorService {

    @Autowired
    private ProductService productService;

    public PriceResponse calculatePrice(List<PriceDto> productList) {

        productService.validatePriceDto(productList);

        double outputTotal = 0;
        double outputTaxes = 0;

        for (PriceDto priceDto : productList) {

            Double salesTax, importTax, taxes, total;

            Product product = productService.getProductByNameAndPrice(priceDto.getName(),priceDto.getPrice());

            salesTax = product.getPrice() * (product.getSalesTax().getTaxValue() / 100);
            importTax = product.getPrice() * (product.getImportDuty().getDutyValue() / 100);

            taxes = getRoundedPrice(salesTax+importTax);
            total = getPriceUptoTwoDecimal(taxes + product.getPrice());

            priceDto.setTaxes(taxes);
            priceDto.setTotal(total);
            priceDto.setImported(product.getImportDuty().getDutyValue()>0);

            outputTotal = outputTotal + total;
            outputTaxes = outputTaxes + taxes;

        }

        PriceResponse priceResponse = new PriceResponse(productList, getRoundedPrice(outputTaxes), outputTotal);

        return priceResponse;
    }

    public double getRoundedPrice(double input) throws CustomException {
        return Math.round(input * 20) / 20.0;
    }

    public double getPriceUptoTwoDecimal(double input) throws CustomException {
        return new BigDecimal(input).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

}
