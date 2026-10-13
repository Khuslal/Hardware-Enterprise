package com.hardware.enterprise.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class User {
	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
}
