package com.hardware.enterprise.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hardware.enterprise.model.Product;
import com.hardware.enterprise.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@GetMapping("/{id}")
	public Optional<Product> getProductById(@PathVariable Long id) { 
		return productService.getProductById(id);
	}
	
	@GetMapping("/")
	public List<Product> getAllProducts() { 
		return productService.getAllProducts();
	}
	
	@PostMapping("/add")
	public void postProduct(@RequestBody Product product) {
		productService.createProduct(product);
	}
	
	@PutMapping("/{id}")
	public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
		product.setId(id);
		productService.save(product);
	}
}
