package com.ingesup.java.carhibou.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ingesup.java.carhibou.data.entities.Itinerary;

public interface ItinerariesRepository extends CrudRepository<Itinerary, Long> {
	List<Itinerary> findAll();
	Itinerary findById(int id);
	@SuppressWarnings("unchecked")
	Itinerary save(Itinerary entity);
}
