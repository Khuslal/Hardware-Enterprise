package com.hardware.enterprise.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hardware.enterprise.model.User;
import com.hardware.enterprise.model.UserPrincipal;
import com.hardware.enterprise.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			System.out.println("No user found!");
			// Let the system know the user not found
			throw new UsernameNotFoundException("User not found!");
		}
		return new UserPrincipal(user);
	}

}
