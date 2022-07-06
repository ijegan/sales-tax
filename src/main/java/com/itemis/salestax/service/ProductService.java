package com.itemis.salestax.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itemis.salestax.dto.ProductDto;
import com.itemis.salestax.exceptions.CustomException;
import com.itemis.salestax.model.Category;
import com.itemis.salestax.model.Product;
import com.itemis.salestax.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> listProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(new ProductDto(product));
        }
        return productDtos;
    }

    public void addProduct(ProductDto productDto, Category category) {
        Product product = new Product(productDto, category);
        productRepository.save(product);
    }

    public void updateProduct(Integer productID, ProductDto productDto, Category category) {
        Product product = new Product(productDto, category);
        product.setId(productID);
        productRepository.save(product);
    }

    public Product getProductById(Integer productId) throws CustomException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new CustomException("Product id is invalid " + productId);
        }
        return optionalProduct.get();
    }

}