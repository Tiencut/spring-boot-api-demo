package com.tutorial.apidemo.apidemo.model;

import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products") 
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false, unique = true, length = 100)
	private String productName;
	private int releaseYear;  // Đổi từ 'year' thành 'releaseYear'
	private Double price;
	private String url;

	// Method tính tuổi sản phẩm (không lưu vào database)
	@Transient
	public int getAge() {
		return Calendar.getInstance().get(Calendar.YEAR) - releaseYear;
	}
}
