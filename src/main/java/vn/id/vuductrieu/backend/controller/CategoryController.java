package vn.id.vuductrieu.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.id.vuductrieu.backend.entity.Category;
import vn.id.vuductrieu.backend.service.CategoryService;
import vn.id.vuductrieu.backend.utils.ResponseUtil;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {this.categoryService = categoryService;}

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        try {
            return ResponseEntity.ok(categoryService.getAllCategories());
        } catch (Exception e) {
            return ResponseUtil.internalServerError();
        }
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        try {
            categoryService.createCategory(category);
            return ResponseUtil.responseWithMessage(HttpStatus.OK, "Category created");
        } catch (IllegalArgumentException e) {
            return ResponseUtil.responseWithMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.internalServerError();
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        try {
            categoryService.updateCategory(category);
            return ResponseUtil.responseWithMessage(HttpStatus.OK, "Category updated");
        } catch (IllegalArgumentException e) {
            return ResponseUtil.responseWithMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.internalServerError();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseUtil.responseWithMessage(HttpStatus.OK, "Category deleted");
        } catch (IllegalArgumentException e) {
            return ResponseUtil.responseWithMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.internalServerError();
        }
    }
}
