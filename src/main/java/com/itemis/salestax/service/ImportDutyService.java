package com.itemis.salestax.service;

import com.itemis.salestax.model.ImportDuty;
import com.itemis.salestax.model.SalesTax;
import com.itemis.salestax.repository.ImportDutyRepository;
import com.itemis.salestax.repository.SalesTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImportDutyService {

    @Autowired
    private final ImportDutyRepository importDutyRepository;

    public ImportDutyService(ImportDutyRepository importDutyRepository) {
        this.importDutyRepository = importDutyRepository;
    }

    public List<ImportDuty> listImportDuty() {
        return importDutyRepository.findAll();
    }

    public void addSalesTax(ImportDuty salesTax) {
        importDutyRepository.save(salesTax);
    }

    public ImportDuty readImportDuty(String name) {
        return importDutyRepository.findByDutyName(name);
    }

    public Optional<ImportDuty> readImportDuty(Integer salesId) {
        return importDutyRepository.findById(salesId);
    }

    public void updateImportDuty(Integer salesID, ImportDuty tax) {
        ImportDuty importyDuty = importDutyRepository.findById(salesID).get();
        importyDuty.setDutyName(tax.getDutyName());
        importyDuty.setDutyValue(tax.getDutyValue());

        importDutyRepository.save(importyDuty);
    }
}
