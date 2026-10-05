package com.hardware.enterprise.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hardware.enterprise.model.Product;
import com.hardware.enterprise.repository.ProductRepo;
import com.hardware.enterprise.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductRepo productRepo;
	
	@Override
	public Product createProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public Optional<Product> getProductById(Long id) {
		return productRepo.findById(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public void updateProduct(Long id, Product product) {
		productRepo.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}

}
