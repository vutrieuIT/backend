package vn.id.vuductrieu.backend.service;

import org.springframework.stereotype.Service;
import vn.id.vuductrieu.backend.entity.Product;
import vn.id.vuductrieu.backend.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {this.productRepository = productRepository;}

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void createProduct(Product product) {
        Optional<Product> existProduct = productRepository.findByName(product.getName());
        if (existProduct.isPresent()) {
            throw new IllegalArgumentException("Product already exists");
        } else {
            productRepository.save(product);
        }
    }

    public void updateProduct(Product product) {
        Optional<Product> existProduct = productRepository.findById(product.getId());
        if (existProduct.isPresent()) {
            productRepository.save(product);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    public void deleteProduct(Long id) {
        Optional<Product> existProduct = productRepository.findById(id);
        if (existProduct.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }
}
