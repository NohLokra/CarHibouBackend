package com.ingesup.java.carhibou.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.ingesup.java.carhibou.services.CommentariesService;
import com.ingesup.java.carhibou.services.ItinerariesService;
import com.ingesup.java.carhibou.services.PointsService;
import com.ingesup.java.carhibou.services.UsersService;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/itineraries")
public class ItinerariesController {

	@Autowired
	ItinerariesService itinerariesService;
	
	@Autowired
	PointsService pointsService;
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	CommentariesService commentariesService;
	
	@PostMapping
	@ResponseBody
	public ApiResponse insert( // Création d'un itinéraire
		@RequestBody ItineraryDTO i,
		@RequestHeader(value = "Authorization", defaultValue = "") String token
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
		
		// Pour une raison que j'ignore, mon IDE me dit que ce code est mort
//		if ( itinerary == null ) {
//			response.setError("Unable to create itinerary");
//		} else {
			response.setResult(itinerary);
//		}
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public ApiResponse getAround( // Recherche d'un itinéraire tant de mètres à la ronde, autour du point donné
		@RequestParam("lat") double latitude,
		@RequestParam("lng") double longitude,
		@RequestParam("radius") int radius,
		@RequestHeader(value = "Authorization", defaultValue = "") String token
	) {
		ApiResponse result = new ApiResponse();
		
		Circle circle = new Circle(latitude, longitude, radius);
		
		if ( token.equals("") ) {
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
	
	@ResponseBody
	@GetMapping("/{id}")
	public ApiResponse findOne(
		@PathVariable("id") int id
	) {
		ApiResponse result = new ApiResponse();
		
		Itinerary i = itinerariesService.findById(id);
		
		result.setResult(i);
		
		return result;
	}

}
