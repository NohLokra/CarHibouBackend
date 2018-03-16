package com.ingesup.java.carhibou.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ingesup.java.carhibou.data.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
	@SuppressWarnings("unchecked")
	User save(User entity);
	User findById(int id);
	User findByToken(String token);
}