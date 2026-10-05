package com.hardware.enterprise.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hardware.enterprise.model.Product;
import com.hardware.enterprise.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@GetMapping("/{id}")
	public Optional<Product> getProductById(@PathVariable Long id) { 
		return productService.getProductById(id);
	}
	
	@GetMapping("/products")
	public List<Product> getAllProducts() { 
		return productService.getAllProducts();
	}
	
	@PostMapping("/create/product")
	public void postProduct(Product product) {
		productService.createProduct(product);
	}
}
