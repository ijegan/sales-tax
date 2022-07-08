package com.itemis.salestax.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.itemis.salestax.common.Response;
import com.itemis.salestax.model.ImportDuty;
import com.itemis.salestax.model.SalesTax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itemis.salestax.dto.ProductDto;
import com.itemis.salestax.exceptions.CustomException;
import com.itemis.salestax.model.Category;
import com.itemis.salestax.model.Product;
import com.itemis.salestax.repository.ProductRepository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
public class ProductService {

    @Autowired
    CategoryService categoryService;
    @Autowired
    SalesTaxService salesTaxService;
    @Autowired
    ImportDutyService importDutyService;

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

    public void validateProductDto(ProductDto productDto){
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        Optional<SalesTax> optionalSalesTax = salesTaxService.readSalesTax(productDto.getSalesTaxId());
        Optional<ImportDuty> optionalImportDuty = importDutyService.readImportDuty(productDto.getImportDutyId());

        List<Product> products = getProductByName(productDto.getName());

        boolean productExists = true;

        for (Product product : products) {
            if (product.getPrice() != productDto.getPrice()) {
                productExists = false;
            }
        }

        if (optionalCategory.isEmpty()) {
            throw new CustomException("Category is invalid ");
        }

        if (optionalSalesTax.isEmpty()) {
            throw new CustomException("SalesTax is invalid ");
        }

        if (optionalImportDuty.isEmpty()) {
            throw new CustomException("ImportDuty is invalid ");
        }

        if (productExists) {
            throw new CustomException("Product already exists");
        }
    }

    public void addProduct(ProductDto productDto){
        Category category = categoryService.readCategory(productDto.getCategoryId()).get();
        SalesTax salesTax = salesTaxService.readSalesTax(productDto.getSalesTaxId()).get();
        ImportDuty importDuty = importDutyService.readImportDuty(productDto.getImportDutyId()).get();

        Product product = new Product(productDto, category, salesTax, importDuty);
        productRepository.save(product);
    }

    public void updateProduct(Integer productID, ProductDto productDto) {
        Category category = categoryService.readCategory(productDto.getCategoryId()).get();
        SalesTax salesTax = salesTaxService.readSalesTax(productDto.getSalesTaxId()).get();
        ImportDuty importDuty = importDutyService.readImportDuty(productDto.getImportDutyId()).get();

        Product product = new Product(productDto, category, salesTax, importDuty);
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

    public List<Product> getProductByName(String name) throws CustomException {
        return productRepository.findByName(name);
    }

    public Product getProductByNameAndPrice(String name,double price) throws CustomException {
        return productRepository.findByNameAndPrice(name,price);
    }

}