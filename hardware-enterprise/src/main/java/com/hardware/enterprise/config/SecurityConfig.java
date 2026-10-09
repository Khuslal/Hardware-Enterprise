package com.hardware.enterprise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http
			.csrf(csrf -> csrf.disable()) // Disable CSRF for localhost Rest APIs
			.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults()) // Enables Basic Auth
			.build();
	}
	
	
	// Defining user details for login security validation
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails user1 = User.withDefaultPasswordEncoder()
							.username("Khush")
							.password("Khush")
							.roles("CUSTOMER")
							.build();
		
		UserDetails user2 = User.withDefaultPasswordEncoder()
				.username("Diwash")
				.password("diwash")
				.roles("MERCHANT")
				.build();
		
		UserDetails user3 = User.withDefaultPasswordEncoder()
				.username("Sailesh")
				.password("sailesh")
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user1, user2, user3);
	}
}
