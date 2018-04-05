package com.ingesup.java.carhibou.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ingesup.java.carhibou.data.dto.ItineraryDTO;
import com.ingesup.java.carhibou.data.entities.Itinerary;
import com.ingesup.java.carhibou.data.entities.Point;
import com.ingesup.java.carhibou.models.ApiResponse;
import com.ingesup.java.carhibou.models.Circle;
import com.ingesup.java.carhibou.services.ItinerariesService;
import com.ingesup.java.carhibou.services.PointsService;
import com.ingesup.java.carhibou.services.UsersService;

@RestController
@RequestMapping(value="/itineraries")
@CrossOrigin(origins = "*")
public class ItinerariesController {

	@Autowired
	ItinerariesService itinerariesService;
	
	@Autowired
	PointsService pointsService;
	
	@Autowired
	UsersService usersService;
	
	@SuppressWarnings("unused")
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ApiResponse insert( // Création d'un itinéraire
		@RequestBody ItineraryDTO i,
		@RequestHeader("Authorization") String token
	) {
		ApiResponse response = new ApiResponse();
		
		Itinerary itinerary = new Itinerary();
		itinerary.setUser(usersService.findByToken(token));
		itinerary.setArrival(i.getArrival());
		itinerary.setStart(i.getStart());
		
		itinerary = itinerariesService.save(itinerary);
		
		List<Point> dbPoints = new ArrayList<Point>();
		for ( Point p : i.getPath() ) {
			p.setItinerary(itinerary);
			
			p = pointsService.save(p);
			
			dbPoints.add(p);
		}
		
		itinerary.setPoints(dbPoints);
		
		if ( itinerary == null ) {
			response.setError("Unable to create itinerary");
		} else {
			response.setResult(itinerary);
		}
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public ApiResponse getAround( // Recherche d'un itinéraire tant de mètres à la ronde, autour du point donné
		@RequestParam("lat") double latitude,
		@RequestParam("lng") double longitude,
		@RequestParam("radius") int radius,
		@RequestHeader("Authorization") String token
	) {
		ApiResponse result = new ApiResponse();
		
		Circle circle = new Circle(latitude, longitude, radius);
		
		if ( token == null ) {
			result.setError("Vous devez être connectés pour accéder à cette fonctionnalité");
		} else {
			List<Itinerary> allItineraries = itinerariesService.findAll();
			List<Itinerary> resultItineraries = new ArrayList<Itinerary>();
			
			for ( Itinerary i : allItineraries ) {
				for ( Point point : i.getPoints() ) {
					if ( circle.hasPoint(point) ) {
						resultItineraries.add(i);
					}
				}
			}
			
			result.setResult(resultItineraries);
		}
		
		return result;
	}
	
}
