package com.dev.rest.entities;

public enum UserRole {
	
	ROLE_USER("ROLE_USER"),
	ROLE_ADMIN("ROLE_ADMIN");
	
	String role;
	
	UserRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return role;
	}
	
}
