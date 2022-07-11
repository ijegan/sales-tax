package com.itemis.salestax.dto;

import javax.validation.constraints.NotNull;

public class PriceDto {

    private int quantity;
    private @NotNull String name;
    private @NotNull double price;

    private boolean imported;

    private double taxes;
    private double total;



    public PriceDto(@NotNull String name, @NotNull double price,
                    @NotNull int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public PriceDto() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }
}