package com.hardware.enterprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hardware.enterprise.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

}
