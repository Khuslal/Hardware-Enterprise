package com.hardware.enterprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hardware.enterprise.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
}
