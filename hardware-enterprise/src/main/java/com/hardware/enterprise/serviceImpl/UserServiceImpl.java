package com.hardware.enterprise.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hardware.enterprise.model.User;
import com.hardware.enterprise.repository.UserRepository;
import com.hardware.enterprise.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepo;
	
	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername();
	}

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	// Update/Save User
	@Override
	public void save(User user) {
		userRepo.save(user);
	}

}
