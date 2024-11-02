package uth.java.duan_java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uth.java.duan_java.models.entities.Product;
import uth.java.duan_java.repositories.ProductRepo;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    // 1. Lấy danh sách tất cả sản phẩm
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepo.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // 2. Lấy sản phẩm theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepo.findById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 3. Thêm sản phẩm mới
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productRepo.save(product);
        return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
    }

    // 4. Cập nhật sản phẩm
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Optional<Product> existingProduct = productRepo.findById(id);
        if (existingProduct.isEmpty()) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        Product product = existingProduct.get();
        product.setName(updatedProduct.getName()); // Cập nhật tên sản phẩm hoặc thuộc tính khác nếu cần
        product.setPrice(updatedProduct.getPrice()); // Cập nhật giá sản phẩm
        product.setDescription(updatedProduct.getDescription()); // Cập nhật mô tả sản phẩm
        productRepo.save(product);
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
    }

    // 5. Xóa sản phẩm
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeProduct(@PathVariable Long id) {
        Optional<Product> existingProduct = productRepo.findById(id);
        if (existingProduct.isEmpty()) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        productRepo.delete(existingProduct.get());
        return new ResponseEntity<>("Product removed successfully", HttpStatus.OK);
    }
}
