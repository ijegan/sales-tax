package com.itemis.salestax;

import com.itemis.salestax.controller.ProductController;
import com.itemis.salestax.dto.PriceDto;
import com.itemis.salestax.dto.ProductDto;
import com.itemis.salestax.model.Category;
import com.itemis.salestax.model.ImportDuty;
import com.itemis.salestax.model.SalesTax;
import com.itemis.salestax.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SalesTaxApplicationTests {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SalesTaxService salesTaxService;
    @Autowired
    ImportDutyService importDutyService;
    @Autowired
    ProductController productController;

    @Autowired
    PriceCalculatorService priceCalculatorService;

    @Test
    public void sales_tax_input1() {

        //add new category
        Category category1 = new Category("others", "any category except food/book/medicine");
        Category category2 = new Category("book", "all genre book");
        Category category3 = new Category("food", "any food related item");
        Category category4 = new Category("medical", "medicine related");

        categoryService.createCategory(category1);
        categoryService.createCategory(category2);
        categoryService.createCategory(category3);
        categoryService.createCategory(category4);

        //add import duty
        ImportDuty importDuty1 = new ImportDuty("none", 0);
        ImportDuty importDuty2 = new ImportDuty("all", 5);

        importDutyService.addImportDuty(importDuty1);
        importDutyService.addImportDuty(importDuty2);

        //add sales tax
        SalesTax salesTax1 = new SalesTax("others", 10);
        SalesTax salesTax2 = new SalesTax("books", 0);
        SalesTax salesTax3 = new SalesTax("food", 0);
        SalesTax salesTax4 = new SalesTax("medical", 0);

        salesTaxService.addSalesTax(salesTax1);
        salesTaxService.addSalesTax(salesTax2);
        salesTaxService.addSalesTax(salesTax3);
        salesTaxService.addSalesTax(salesTax4);

        //add product
        ProductDto productDto1= new ProductDto("book",12.49,"scifi book",2,2,1);
        ProductDto productDto2= new ProductDto("music CD",14.99,"musical casette",1,1,1);
        ProductDto productDto3= new ProductDto("chocolate bar",0.85,"",3,3,1);

        productService.addProduct(productDto1);
        productService.addProduct(productDto2);
        productService.addProduct(productDto3);

        //Test case 1
        PriceDto price1=new PriceDto("book",12.49,1);
        PriceDto price2=new PriceDto("music CD",14.99,1);
        PriceDto price3=new PriceDto("chocolate bar",0.85,1);

        List<PriceDto> priceList=new ArrayList<>();
        priceList.add(price1);
        priceList.add(price2);
        priceList.add(price3);

        List<PriceDto> priceDtos = priceCalculatorService.calculatePrice(priceList);

        for(PriceDto price: priceDtos){
            System.out.println("** .. "+price.getName() + "  " + price.getTotal() + "  " + price.getTaxes());
        }

        //Test case 2

        ProductDto pd1= new ProductDto("chocolates",10,"box of chocolate",3,3,2);
        ProductDto pd2= new ProductDto("perfume",47.50,"bottle of perfume",1,1,2);
        productService.addProduct(pd1);
        productService.addProduct(pd2);

        PriceDto p1=new PriceDto("chocolates",10,1);
        PriceDto p2=new PriceDto("perfume",47.50,1);

        List<PriceDto> priceList2=new ArrayList<>();
        priceList2.add(p1);
        priceList2.add(p2);

        List<PriceDto> priceDtos2 = priceCalculatorService.calculatePrice(priceList2);
        System.out.println();
        for(PriceDto price: priceDtos2){
            System.out.println("** .. "+price.getName() + "  " + price.getTotal() + "  " + price.getTaxes());
        }


        //Test case 3

        ProductDto c1= new ProductDto("perfume",32.19,"imported bottle of perfume",1,1,2);
        ProductDto c2= new ProductDto("perfume",20.89,"bottle of perfume",1,1,1);
        ProductDto c3= new ProductDto("headache pills",9.75,"",4,4,1);
        ProductDto c4= new ProductDto("chocolates",11.85,"",3,3,2);

        productService.addProduct(c1);
        productService.addProduct(c2);
        productService.addProduct(c3);
        productService.addProduct(c4);

        PriceDto pc1=new PriceDto("perfume",32.19,1);
        PriceDto pc2=new PriceDto("perfume",20.89,1);
        PriceDto pc3=new PriceDto("headache pills",9.75,1);
        PriceDto pc4=new PriceDto("chocolates",11.85,1);

        List<PriceDto> plc3=new ArrayList<>();
        plc3.add(pc1);
        plc3.add(pc2);
        plc3.add(pc3);
        plc3.add(pc4);

        List<PriceDto> priceDtoList3 = priceCalculatorService.calculatePrice(plc3);
        System.out.println();
        for(PriceDto price: priceDtoList3){
            System.out.println("** .. "+price.getName() + "  " + price.getTotal() + "  " + price.getTaxes());
        }

//        assertEquals("food", category2.getCategoryName());
    }
}
