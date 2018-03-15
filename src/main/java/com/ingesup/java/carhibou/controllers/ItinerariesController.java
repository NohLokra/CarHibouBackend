package com.ingesup.java.carhibou.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ingesup.java.carhibou.data.entities.Itinerary;
import com.ingesup.java.carhibou.models.PostItinerary;

@RestController
@RequestMapping("/itineraries")
public class ItinerariesController {
	
	@RequestMapping(method=RequestMethod.POST)
	public Itinerary insert(
		@RequestParam("itinerary") PostItinerary i
	) {
		return null;
	}
}
