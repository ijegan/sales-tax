package com.itemis.salestax.common;

import com.itemis.salestax.dto.PriceDto;

import java.time.LocalDateTime;
import java.util.List;

public class PriceResponse {
	private final List<PriceDto> priceDtoList;
	private final double salesTaxes;
	private final double total;

	public PriceResponse(List<PriceDto> priceDtoList, double salesTaxes, double total) {
		this.priceDtoList = priceDtoList;
		this.salesTaxes = salesTaxes;
		this.total = total;
	}

	public List<PriceDto> getPriceDtoList() {
		return priceDtoList;
	}

	public double getSalesTaxes() {
		return salesTaxes;
	}

	public double getTotal() {
		return total;
	}

	public String getTimestamp() {
		return LocalDateTime.now().toString();
	}

	public String toString() {

		StringBuilder sb=new StringBuilder();
		priceDtoList.forEach(price->{
			String imported= price.isImported() ? "imported " : "";
			sb.append(price.getQuantity()+" "+imported+price.getName()+": "+price.getTotal()+" \n");
		});
		sb.append("salesTaxes: "+salesTaxes+" \ntotal: "+total+"\n");

		return sb.toString();
	}

}