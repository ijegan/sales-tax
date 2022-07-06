package com.itemis.salestax.dto;

import javax.validation.constraints.NotNull;

import com.itemis.salestax.model.Product;

public class ProductDto {

	private Integer id;
	private @NotNull String name;
	private @NotNull double price;
	private String description;
	private @NotNull Integer categoryId;

	public ProductDto(Product product) {
		this.setId(product.getId());
		this.setName(product.getName());
		this.setPrice(product.getPrice());
		this.setDescription(product.getDescription());
		this.setCategoryId(product.getCategory().getId());
	}

	public ProductDto(@NotNull String name, @NotNull double price,
					  @NotNull String description, @NotNull Integer categoryId) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.categoryId = categoryId;
	}

	public ProductDto() {
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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
}