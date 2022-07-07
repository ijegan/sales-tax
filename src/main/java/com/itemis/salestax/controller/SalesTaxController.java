package com.itemis.salestax.controller;

import com.itemis.salestax.common.Response;
import com.itemis.salestax.model.SalesTax;
import com.itemis.salestax.service.SalesTaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sales-tax")

public class SalesTaxController {

	@Autowired
	private SalesTaxService salesTaxService;

	@GetMapping("/")
	public ResponseEntity<List<SalesTax>> getSalesInformation() {
		List<SalesTax> body = salesTaxService.listSalesInformation();
		return new ResponseEntity<List<SalesTax>>(body, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Response> addSalesTax(@Valid @RequestBody SalesTax salesTax) {

		if (salesTaxService.readSalesTax(salesTax.getTaxName()) != null) {
			return new ResponseEntity<Response>(new Response(false, "sales tax already exists"),
					HttpStatus.CONFLICT);
		}
		salesTaxService.addSalesTax(salesTax);
		return new ResponseEntity<Response>(new Response(true, "created sales tax"), HttpStatus.CREATED);
	}

	@PostMapping("/update/{salesID}")
	public ResponseEntity<Response> updateCategory(@PathVariable("salesID") Integer salesID,
			@Valid @RequestBody SalesTax tax) {
		// Check to see if the category exists.
		if (salesTaxService.readSalesTax(salesID).isPresent()) {
			// If the category exists then update it.
			salesTaxService.updateSalesTax(salesID, tax);
			return new ResponseEntity<Response>(new Response(true, "updated sales tax"), HttpStatus.OK);
		}

		// If the category doesn't exist then return a response of unsuccessful.
		return new ResponseEntity<Response>(new Response(false, "salestax does not exist"), HttpStatus.NOT_FOUND);
	}
}
