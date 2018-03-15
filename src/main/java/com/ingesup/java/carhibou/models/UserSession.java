package com.ingesup.java.carhibou.models;

import java.util.UUID;

import com.ingesup.java.carhibou.data.entities.User;

public class UserSession {
	private String token;
	private User user;
	
	public UserSession(User user) {
		this.user = user;
		this.token = generateToken();
	}
	
	protected String generateToken() {
		UUID uuid = UUID.randomUUID();
		
		return uuid.toString();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
