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

import com.itemis.salestax.common.ApiResponse;
import com.itemis.salestax.common.Helper;
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
		categoryService.createCategory(
				new Category("books", "Books are classified as either fiction/nonfiction/adventure/.. "));
		List<Category> body = categoryService.listCategories();
		return new ResponseEntity<List<Category>>(body, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
		if (Helper.notNull(categoryService.readCategory(category.getCategoryName()))) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"),
					HttpStatus.CONFLICT);
		}
		categoryService.createCategory(category);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
	}

	@PostMapping("/update/{categoryID}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") Integer categoryID,
			@Valid @RequestBody Category category) {
		// Check to see if the category exists.
		if (Helper.notNull(categoryService.readCategory(categoryID))) {
			// If the category exists then update it.
			categoryService.updateCategory(categoryID, category);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
		}

		// If the category doesn't exist then return a response of unsuccessful.
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
	}
}
