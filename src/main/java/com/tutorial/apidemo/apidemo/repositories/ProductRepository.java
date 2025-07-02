package com.tutorial.apidemo.apidemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.apidemo.apidemo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    
}
