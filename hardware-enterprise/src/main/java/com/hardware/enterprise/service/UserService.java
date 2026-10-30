package com.hardware.enterprise.service;

import java.util.List;
import com.hardware.enterprise.model.User;

public interface UserService {
	User findByUsername(String username);
	List<User> findAll();
	// Update/Save User
	void save(User user);
}
