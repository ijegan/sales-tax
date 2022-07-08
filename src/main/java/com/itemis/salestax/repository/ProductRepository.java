package com.itemis.salestax.repository;

import com.itemis.salestax.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByName(String name);
    Product findByNameAndPrice(String name,double price);
}