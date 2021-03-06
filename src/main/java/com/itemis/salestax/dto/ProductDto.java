package com.itemis.salestax.dto;

import javax.validation.constraints.NotNull;

import com.itemis.salestax.model.ImportDuty;
import com.itemis.salestax.model.Product;
import org.springframework.context.annotation.Import;

public class ProductDto {

	private Integer id;
	private @NotNull String name;
	private @NotNull double price;
	private String description;
	private @NotNull Integer categoryId;
	private @NotNull Integer salesTaxId;
    private @NotNull Integer importDutyId;

	public ProductDto(Product product) {
		this.setId(product.getId());
		this.setName(product.getName());
		this.setPrice(product.getPrice());
		this.setDescription(product.getDescription());
		this.setCategoryId(product.getCategory().getId());
		this.setSalesTaxId(product.getSalesTax().getTaxId());
        this.setImportDutyId(product.getImportDuty().getDutyId());
	}

	public ProductDto(@NotNull String name, @NotNull double price,
					  @NotNull String description, @NotNull Integer categoryId, @NotNull Integer salesTaxId, @NotNull Integer importDutyId) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.categoryId = categoryId;
		this.salesTaxId = salesTaxId;
        this.importDutyId = importDutyId;
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

	public Integer getSalesTaxId() {
		return salesTaxId;
	}

	public void setSalesTaxId(Integer salesTaxId) {
		this.salesTaxId = salesTaxId;
	}

    public Integer getImportDutyId() {
        return importDutyId;
    }

    public void setImportDutyId(Integer importDutyId) {
        this.importDutyId = importDutyId;
    }

}