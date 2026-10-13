package com.hardware.enterprise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// 1. Password Encoding
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 

	// 2. Authentication
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}
	
	// For strict multiple security chain
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception{
		return authConfig.getAuthenticationManager();
	}
	
	// 3. Authorization
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
				.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN") /* only admin can access post request */
				.requestMatchers(HttpMethod.GET, "/api/**").permitAll() // anyone can access get request

				// .anyReq().auth() ensures that any other url request except
				// the above rules requires authentication (e.g /products, /home)
				.anyRequest().authenticated())
				// for web application
				.formLogin(Customizer.withDefaults())

				// for mobile app, postman
				.httpBasic(Customizer.withDefaults()).build();
	}

	/* 	
	 * Defining user details for login security validation
	 *	We don't required hard coded value but for basic understanding we implemented
	 *	this.
	 **/
	
	/*
	  @Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails customer = User.withDefaultPasswordEncoder()
							.username("Khush")
							.password("Khush")
							.roles("CUSTOMER")
							.build();
		
		UserDetails merchant = User.withDefaultPasswordEncoder()
				.username("Diwash")
				.password("diwash")
				.roles("MERCHANT")
				.build();
		
		UserDetails admin = User.withDefaultPasswordEncoder()
				.username("Sailesh")
				.password("sailesh")
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(admin, customer, merchant);
	
	}
	 */

}
