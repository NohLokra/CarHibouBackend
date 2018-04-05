package com.ingesup.java.carhibou.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingesup.java.carhibou.data.entities.User;
import com.ingesup.java.carhibou.repositories.UserRepository;

@Service
public class UsersService {
	@Autowired
	UserRepository daoUser;
	
	public boolean authenticate(String username, String password) {
		User u = daoUser.findByUsername(username);
		
		System.out.println("Identification de " + username + " avec le mdp " + password);
		if ( u == null ) {
			return false;
		} else {
			String hashedPassword = u.getPassword();
			
			return password.equals(hashedPassword);
		}
	}
	
	public User findByUsername(String username) {
		return daoUser.findByUsername(username);
	}
	
	public User findByEmail(String email) {
		return daoUser.findByEmail(email);
	}
	
	public User findById(int id) {
		return daoUser.findById(id);
	}
	
	public User create(User u) {
		return daoUser.save(u);
	}
	
	public User findByToken(String token) {
		return daoUser.findByToken(token);
	}
	
	public User save(User u) {
		return daoUser.save(u);
	}
}

