package com.itemis.salestax.service;

import com.itemis.salestax.model.SalesTax;
import com.itemis.salestax.repository.SalesTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalesTaxService {

    @Autowired
    private final SalesTaxRepository salesTaxRepository;

    public SalesTaxService(SalesTaxRepository salesTaxRepository) {
        this.salesTaxRepository = salesTaxRepository;
    }

    public List<SalesTax> listSalesInformation() {
        return salesTaxRepository.findAll();
    }

    public void addSalesTax(SalesTax salesTax) {
        salesTaxRepository.save(salesTax);
    }

    public SalesTax readSalesTax(String name) {
        return salesTaxRepository.findByTaxName(name);
    }

    public Optional<SalesTax> readSalesTax(Integer salesId) {
        return salesTaxRepository.findById(salesId);
    }

    public void updateSalesTax(Integer salesID, SalesTax tax) {
        SalesTax salesTax = salesTaxRepository.findById(salesID).get();
        salesTax.setTaxName(tax.getTaxName());
        salesTax.setTaxValue(tax.getTaxValue());

        salesTaxRepository.save(salesTax);
    }
}
