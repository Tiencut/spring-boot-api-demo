package com.tutorial.apidemo.apidemo.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tutorial.apidemo.apidemo.model.Product;
import com.tutorial.apidemo.apidemo.repositories.ProductRepository;

@Configuration
public class Database {

    // logger
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {

        return args -> {
            // Tạo dữ liệu mẫu bằng Lambda (cách hiện đại)
            Product product1 = Product.builder()
                .productName("iPhone 15")
                .releaseYear(2023)  // Đổi từ 'year' thành 'releaseYear'
                .price(999.99)
                .url("https://apple.com/iphone15")
                .build();
                
            Product product2 = Product.builder()
                .productName("Samsung Galaxy S24")
                .releaseYear(2024)  // Đổi từ 'year' thành 'releaseYear'
                .price(899.99)
                .url("https://samsung.com/galaxy-s24")
                .build();
                
            logger.info("insert data: " + productRepository.save(product1));
            logger.info("insert data: " + productRepository.save(product2));
            
        };
    }
}
