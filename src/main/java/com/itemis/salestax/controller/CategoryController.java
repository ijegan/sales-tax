package com.itemis.salestax.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itemis.salestax.common.Response;
import com.itemis.salestax.model.Category;
import com.itemis.salestax.service.CategoryService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/category")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<List<Category>>(body, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Response> createCategory(@Valid @RequestBody Category category) {
        if (categoryService.readCategory(category.getCategoryName()) != null) {
            return new ResponseEntity<Response>(new Response(false, "category already exists"),
                    HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<Response>(new Response(true, "created the category"), HttpStatus.CREATED);
    }

    @PostMapping("/update/{categoryID}")
    public ResponseEntity<Response> updateCategory(@PathVariable("categoryID") Integer categoryID,
                                                   @Valid @RequestBody Category category) {
        // Check to see if the category exists.
        if (categoryService.readCategory(categoryID) != null) {
            // If the category exists then update it.
            categoryService.updateCategory(categoryID, category);
            return new ResponseEntity<Response>(new Response(true, "updated the category"), HttpStatus.OK);
        }

        // If the category doesn't exist then return a response of unsuccessful.
        return new ResponseEntity<Response>(new Response(false, "category does not exist"), HttpStatus.NOT_FOUND);
    }
}
