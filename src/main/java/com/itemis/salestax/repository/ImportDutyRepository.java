package com.itemis.salestax.repository;

import com.itemis.salestax.model.ImportDuty;
import com.itemis.salestax.model.SalesTax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportDutyRepository extends JpaRepository<ImportDuty, Integer> {
	ImportDuty findByDutyName(String taxName);
}
