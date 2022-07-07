package com.itemis.salestax.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class ImportDuty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dutyId;

	@Column(name = "duty_name")
	private @NotBlank String dutyName;

	@Column(name = "duty_value")
	private double dutyValue;

	@OneToMany(mappedBy = "importDuty", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Set<Product> productSet;

	public ImportDuty() {
	}

	public ImportDuty(String dutyName, double dutyValue) {
		this.dutyName = dutyName;
		this.dutyValue = dutyValue;
	}

	public Integer getDutyId() {
		return dutyId;
	}

	public void setDutyId(Integer dutyId) {
		this.dutyId = dutyId;
	}

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public double getDutyValue() {
		return dutyValue;
	}

	public void setDutyValue(double dutyValue) {
		this.dutyValue = dutyValue;
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
		if (!(o instanceof ImportDuty)) return false;
		ImportDuty that = (ImportDuty) o;
		return Double.compare(that.dutyValue, dutyValue) == 0 && dutyId.equals(that.dutyId) && dutyName.equals(that.dutyName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dutyId, dutyName, dutyValue);
	}
}
