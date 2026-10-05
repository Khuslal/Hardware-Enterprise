package com.hardware.enterprise.service;

import java.util.List;
import java.util.Optional;

import com.hardware.enterprise.model.Product;

public interface ProductService{
	// 1. Create a new product
	Product createProduct(Product product);
	
	// 2. Get product by id
	Optional<Product> getProductById(Long id);
	
	// 3. Get all products
	List<Product> getAllProducts();
	
	// 4. Update product by id
	Optional<Product> updateProduct(Long id, Product product);
	
	// 5. Delete product by id
	void deleteProduct(Long id);
}
