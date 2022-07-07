package com.itemis.salestax.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class SalesTax {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer taxId;

	@Column(name = "tax_name")
	private @NotBlank String taxName;

	@Column(name = "tax_value")
	private double taxValue;

	@OneToMany(mappedBy = "salesTax", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Set<Product> productSet;

	public SalesTax() {
	}

	public SalesTax(String taxName, double taxValue) {
		this.taxName = taxName;
		this.taxValue = taxValue;
	}

	public Integer getTaxId() {
		return taxId;
	}

	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public double getTaxValue() {
		return taxValue;
	}

	public void setTaxValue(double taxValue) {
		this.taxValue = taxValue;
	}

	public Set<Product> getProductSet() {
		return productSet;
	}

	public void setProductSet(Set<Product> productSet) {
		this.productSet = productSet;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SalesTax)) return false;
		SalesTax salesTax = (SalesTax) o;
		return Double.compare(salesTax.taxValue, taxValue) == 0 && taxId.equals(salesTax.taxId) && taxName.equals(salesTax.taxName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(taxId, taxName, taxValue);
	}
}
