package com.ingesup.java.carhibou.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ingesup.java.carhibou.data.dto.CommentaryDTO;
import com.ingesup.java.carhibou.data.entities.Commentary;
import com.ingesup.java.carhibou.data.entities.User;
import com.ingesup.java.carhibou.models.ApiResponse;
import com.ingesup.java.carhibou.services.CommentariesService;
import com.ingesup.java.carhibou.services.ItinerariesService;
import com.ingesup.java.carhibou.services.UsersService;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/commentaries")
public class CommentariesController {

	@Autowired
	ItinerariesService itinerariesService;
	
	@Autowired
	CommentariesService commentariesService;
	
	@Autowired
	UsersService usersService;
	
	@ResponseBody
	@PostMapping
	public ApiResponse addCommentary(
		@RequestBody CommentaryDTO commentaryDto,
		@RequestHeader(value = "Authorization", defaultValue = "") String token
	) {
		ApiResponse response = new ApiResponse();
		
		if ( token.equals("") ) {
			response.setError("You must be logged in in order to access this function");
			
			return response;
		}
		
		User u = usersService.findByToken(token);
		if ( u == null ) {
			response.setError("You must have a valid session in order to access this function");
			
			return response;
		}
		
		Commentary commentary = new Commentary();
		commentary.setContent(commentaryDto.getContent());
		commentary.setItinerary(itinerariesService.findById(commentaryDto.getItinerary_id()));
		
		commentary = commentariesService.save(commentary);
		
		if ( commentary == null ) {
			response.setError("Unable to create commentary");
		} else {
			response.setResult(commentary);
		}
		
		return response;
	}
	
	@ResponseBody
	@GetMapping
	public ApiResponse get(
			@RequestParam("itinerary") int id
	) {
		ApiResponse result = new ApiResponse();
		
		List<Commentary> commentaries = commentariesService.findByItinerary(id);
		
		result.setResult(commentaries);
		
		return result;
	}
}