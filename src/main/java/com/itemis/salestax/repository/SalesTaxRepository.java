package com.itemis.salestax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itemis.salestax.model.SalesTax;

@Repository
public interface SalesTaxRepository extends JpaRepository<SalesTax, Integer> {
	SalesTax findByTaxName(String taxName);
}
