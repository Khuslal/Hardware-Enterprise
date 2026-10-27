package com.hardware.enterprise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// 1. Password Encoding
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 2. Authentication
	
	private final UserDetailsService userDetailsService;
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
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

		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/auth/register", "/error").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN") /* only admin can access post request */
						.requestMatchers(HttpMethod.GET, "/api/**").permitAll() // anyone can access get request
						.anyRequest().authenticated()
						)
/* .anyReq().authenticated() ensures that any other url request except the above
 rules requires authentication (e.g /products, /home) */
				// for web application
						.formLogin(form -> form
								.loginPage("/login")
								.loginProcessingUrl("/login")
								.usernameParameter("email")
								.passwordParameter("password")
								.defaultSuccessUrl("/dashboard", true)
								.permitAll()
						)
						.logout(logout -> logout
				                .logoutUrl("/logout")
				                .logoutSuccessUrl("/?logout=true")
				                .permitAll()
				        )

				// for mobile app, postman
						.httpBasic(Customizer.withDefaults())
						.build();
	}

}
