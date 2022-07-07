package com.itemis.salestax.service;

import com.itemis.salestax.model.Category;
import com.itemis.salestax.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category readCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    public Optional<Category> readCategory(Integer id) {
        return categoryRepository.findById(id);
    }

    public void updateCategory(Integer categoryId, Category newCategory) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            category.get().setCategoryName(newCategory.getCategoryName());
            category.get().setDescription(newCategory.getDescription());
            categoryRepository.save(category.get());
        }

    }
}
