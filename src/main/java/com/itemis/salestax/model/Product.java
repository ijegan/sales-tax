package com.itemis.salestax.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itemis.salestax.dto.ProductDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private @NotNull String name;
	private @NotNull double price;
	private String description;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	Category category;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "tax_id", nullable = false)
	SalesTax salesTax;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "duty_id", nullable = false)
	ImportDuty importDuty;

	public Product(String name, double price, String description, Category category, SalesTax salesTax, ImportDuty importDuty) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.category = category;
		this.salesTax = salesTax;
		this.importDuty = importDuty;
	}

	public Product(ProductDto productDto, Category category, SalesTax salesTax, ImportDuty importDuty) {
		this.name = productDto.getName();
		this.description = productDto.getDescription();
		this.price = productDto.getPrice();
		this.category = category;
		this.salesTax = salesTax;
		this.importDuty = importDuty;
	}

	public Product() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SalesTax getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(SalesTax salesTax) {
		this.salesTax = salesTax;
	}

	public ImportDuty getImportDuty() {
		return importDuty;
	}

	public void setImportDuty(ImportDuty importDuty) {
		this.importDuty = importDuty;
	}

	@Override
	public String toString() {
		return "Product{" + "id=" + id + ", name='" + name + '\'' + ",'" + '\'' + ", price=" + price + ", description='"
				+ description + '\'' + '}';
	}
}
