package com.ingesup.java.carhibou.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ingesup.java.carhibou.data.entities.Commentary;

public interface CommentariesRepository extends CrudRepository <Commentary, Long> {
	Commentary findById(int id);
	List<Commentary> findByItinerary(int itinerary_id);
	Commentary save(Commentary entity);
}
