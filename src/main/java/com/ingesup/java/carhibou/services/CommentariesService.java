package com.ingesup.java.carhibou.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ingesup.java.carhibou.data.entities.Commentary;
import com.ingesup.java.carhibou.repositories.CommentariesRepository;

public class CommentariesService {
	@Autowired
	CommentariesRepository daoCommentary;
	
	public List<Commentary> findByItinerary(int itinerary_id) {
		return daoCommentary.findByItinerary(itinerary_id);
	}
	
	public Commentary save(Commentary entity) {
		return daoCommentary.save(entity);
	}
	
	public Commentary findById(int id) {
		return daoCommentary.findById(id);
	}
}
