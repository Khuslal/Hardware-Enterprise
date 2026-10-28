package com.hardware.enterprise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hardware.enterprise.model.User;

@Service
public interface UserService {
	User findUserByUsername(String username);
	List<User> findByUsername(String username);
}
