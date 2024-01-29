package vn.id.vuductrieu.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.id.vuductrieu.backend.entity.Product;
import vn.id.vuductrieu.backend.service.ProductService;
import vn.id.vuductrieu.backend.utils.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {this.productService = productService;}

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(Product product) {
        try {
            productService.createProduct(product);
            return ResponseUtil.responseWithMessage(HttpStatus.OK, "Product created");
        } catch (IllegalArgumentException e) {
            return ResponseUtil.responseWithMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.internalServerError();
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateProduct(Product product) {
        try {
            productService.updateProduct(product);
            return ResponseUtil.responseWithMessage(HttpStatus.OK, "Product updated");
        } catch (IllegalArgumentException e) {
            return ResponseUtil.responseWithMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.internalServerError();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseUtil.responseWithMessage(HttpStatus.OK, "Product deleted");
        } catch (IllegalArgumentException e) {
            return ResponseUtil.responseWithMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.internalServerError();
        }
    }
}
