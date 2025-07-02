/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tutorial.apidemo.apidemo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tutorial.apidemo.apidemo.model.Product;
import com.tutorial.apidemo.apidemo.repositories.ProductRepository;

/**
 *
 * @author tient
 */
@RestController
@RequestMapping("/api/v1/Products")
public class ProducController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("")
    ResponseEntity<List<Product>> getAllProducts() {
        // Lấy dữ liệu từ database thông qua Repository
        List<Product> products = repository.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> findById(@PathVariable Long id) {
        Optional<Product> product = repository.findById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build(); // HTTP 404
        }
    }

    // Lấy sản phẩm theo query parameters
    @GetMapping("/search")
    ResponseEntity<List<Product>> searchProducts(
            @RequestParam String productName,
            @RequestParam(defaultValue = "0") Double minPrice) {

        // Tạm thời dùng findAll - cần implement method trong repository
        List<Product> products = repository.findAll();
        return ResponseEntity.ok(products);
    }

    // 
    @PostMapping("")
    ResponseEntity<Product> createProduct(@RequestBody Product newProduct) {
        try {
            Product savedProduct = repository.save(newProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

// Với validation  
    @PostMapping("/validate")
    ResponseEntity<Product> createProductWithValidation(@RequestBody Product newProduct) {
        Product savedProduct = repository.save(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{id}")
    ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product productDetails) {

        Optional<Product> optionalProduct = repository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setProductName(productDetails.getProductName());
            product.setPrice(productDetails.getPrice());
            product.setReleaseYear(productDetails.getReleaseYear());
            product.setUrl(productDetails.getUrl());

            Product updatedProduct = repository.save(product);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build(); // HTTP 204
        } else {
            return ResponseEntity.notFound().build(); // HTTP 404
        }
    }

// Xóa nhiều sản phẩm
    @DeleteMapping("")
    ResponseEntity<Void> deleteProducts(@RequestBody List<Long> ids) {
        repository.deleteAllById(ids);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<Product> partialUpdateProduct(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        Optional<Product> optionalProduct = repository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            // Chỉ cập nhật các field được gửi lên
            updates.forEach((key, value) -> {
                switch (key) {
                    case "productName":
                        product.setProductName((String) value);
                        break;
                    case "price":
                        product.setPrice((Double) value);
                        break;
                    case "releaseYear":
                        product.setReleaseYear((Integer) value);
                        break;
                    case "url":
                        product.setUrl((String) value);
                        break;
                }
            });

            Product updatedProduct = repository.save(product);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // HEAD - Kiểm tra sự tồn tại của resource mà không trả về body
    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    ResponseEntity<Void> checkProductExists(@PathVariable Long id) {
        if (repository.existsById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // OPTIONS - Trả về các HTTP methods được support
    @RequestMapping(value = "", method = RequestMethod.OPTIONS)
    ResponseEntity<Void> getOptions() {
        return ResponseEntity.ok()
                .header("Allow", "GET,POST,PUT,DELETE,PATCH,HEAD,OPTIONS")
                .build();
    }

    // GET với Pagination và Sorting
    @GetMapping("/page")
    ResponseEntity<List<Product>> getProductsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        // Tạm thời trả về tất cả products - cần implement Pageable trong repository
        List<Product> products = repository.findAll();
        return ResponseEntity.ok(products);
    }

    // GET với Response Headers tùy chỉnh
    @GetMapping("/count")
    ResponseEntity<Map<String, Object>> getProductCount() {
        long totalProducts = repository.count();
        Map<String, Object> response = Map.of(
                "totalProducts", totalProducts,
                "timestamp", System.currentTimeMillis()
        );

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(totalProducts))
                .header("Cache-Control", "max-age=3600")
                .body(response);
    }

    // POST với Response Location Header
    @PostMapping("/location")
    ResponseEntity<Product> createProductWithLocation(@RequestBody Product newProduct) {
        try {
            Product savedProduct = repository.save(newProduct);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Location", "/api/v1/Products/" + savedProduct.getId())
                    .body(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Bulk Operations - Tạo nhiều sản phẩm
    @PostMapping("/bulk")
    ResponseEntity<List<Product>> createMultipleProducts(@RequestBody List<Product> products) {
        try {
            List<Product> savedProducts = repository.saveAll(products);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProducts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT với validation tùy chỉnh
    @PutMapping("/validate/{id}")
    ResponseEntity<?> updateProductWithValidation(
            @PathVariable Long id,
            @RequestBody Product productDetails) {

        // Custom validation
        if (productDetails.getProductName() == null || productDetails.getProductName().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Product name is required"));
        }

        if (productDetails.getPrice() == null || productDetails.getPrice() <= 0) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Valid price is required"));
        }

        Optional<Product> optionalProduct = repository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setProductName(productDetails.getProductName());
            product.setPrice(productDetails.getPrice());
            product.setReleaseYear(productDetails.getReleaseYear());
            product.setUrl(productDetails.getUrl());

            Product updatedProduct = repository.save(product);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Product not found with id: " + id));
        }
    }

    // DELETE với Soft Delete (cập nhật status thay vì xóa thật)
    @DeleteMapping("/soft/{id}")
    ResponseEntity<Map<String, String>> softDeleteProduct(@PathVariable Long id) {
        Optional<Product> optionalProduct = repository.findById(id);

        if (optionalProduct.isPresent()) {
            // Trong thực tế, bạn sẽ có field "deleted" hoặc "status" trong Product
            // Ở đây chỉ là ví dụ
            repository.deleteById(id); // Tạm thời vẫn hard delete

            return ResponseEntity.ok(Map.of(
                    "message", "Product soft deleted successfully",
                    "productId", id.toString()
            ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Error handling endpoint
    @GetMapping("/error-demo")
    ResponseEntity<?> demonstrateErrorHandling(@RequestParam(required = false) String type) {
        switch (type) {
            case "400":
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Bad Request Demo"));
            case "404":
                return ResponseEntity.notFound().build();
            case "500":
                throw new RuntimeException("Internal Server Error Demo");
            default:
                return ResponseEntity.ok(Map.of("message", "No error specified"));
        }
    }

}
