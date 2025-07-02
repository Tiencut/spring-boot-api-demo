package com.tutorial.apidemo.apidemo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;

public class ProductTest {
    
    @Test
    public void testProductAge() {
        // Arrange
        Product product = Product.builder()
            .productName("iPhone 12")
            .releaseYear(2020)
            .price(999.0)
            .url("https://apple.com")
            .build();
        
        // Act
        int age = product.getAge();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int expectedAge = currentYear - 2020;
        
        // Assert
        assertEquals(expectedAge, age);
        System.out.println("Product age: " + age + " years");
        System.out.println("Current year: " + currentYear);
    }
    
    @Test
    public void testProductFields() {
        // Test Lombok generated methods
        Product product = new Product();
        product.setProductName("Test Product");
        product.setReleaseYear(2023);
        product.setPrice(500.0);
        product.setUrl("https://test.com");
        
        assertEquals("Test Product", product.getProductName());
        assertEquals(2023, product.getReleaseYear());
        assertEquals(500.0, product.getPrice());
        assertEquals("https://test.com", product.getUrl());
    }
}
