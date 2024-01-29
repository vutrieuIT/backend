package vn.id.vuductrieu.backend.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import vn.id.vuductrieu.backend.entity.Category;
import vn.id.vuductrieu.backend.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {this.categoryRepository = categoryRepository;}


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return category.get();
        } else {
            throw new EmptyResultDataAccessException("Category not found", 1);
        }
    }

    public void createCategory(Category category) {
        Optional<Category> existedCategory = categoryRepository.findByName(category.getName());
        if (existedCategory.isPresent()) {
            throw new IllegalArgumentException("Category name already exists");
        }
        categoryRepository.save(category);
    }

    public void updateCategory(Category category) {
        Optional<Category> existedCategory = categoryRepository.findByName(category.getName());
        if (existedCategory.isPresent() && !existedCategory.get().getId().equals(category.getId())) {
            throw new IllegalArgumentException("Category name already exists");
        }
        categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.delete(category.get());
        } else {
            throw new EmptyResultDataAccessException("Category not found", 1);
        }
    }
}
