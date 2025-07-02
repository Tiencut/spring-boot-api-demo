package com.tutorial.apidemo.apidemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;  // Changed from int to Long
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String fullname;
    
    @Column(nullable = false)
    private String hash_password;
    
    @Enumerated(EnumType.STRING)
    private State state;
}
