package com.ingesup.java.carhibou.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingesup.java.carhibou.data.entities.Itinerary;
import com.ingesup.java.carhibou.repositories.ItinerariesRepository;

@Service
public class ItinerariesService {
	
	@Autowired
	ItinerariesRepository daoItineraries;
	
	public List<Itinerary> findAll() {
		return daoItineraries.findAll();
	}
	
	public Itinerary save(Itinerary entity) {
		return daoItineraries.save(entity);
	}

}
