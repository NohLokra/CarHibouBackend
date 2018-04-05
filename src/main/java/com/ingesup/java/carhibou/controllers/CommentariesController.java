package com.ingesup.java.carhibou.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ingesup.java.carhibou.data.entities.Commentary;
import com.ingesup.java.carhibou.models.ApiResponse;
import com.ingesup.java.carhibou.services.CommentariesService;
import com.ingesup.java.carhibou.services.ItinerariesService;

public class CommentariesController {

	@Autowired
	ItinerariesService itinerariesService;
	
	@Autowired
	CommentariesService commentariesService;
	
	@ResponseBody
	@RequestMapping(value="/addCommentary", method=RequestMethod.POST)
	public ApiResponse addCommentary(
			@RequestParam("content") String content,
			@RequestParam("itineraryId") int itineraryId
	){
		ApiResponse response = new ApiResponse();
		Commentary commentary = new Commentary();
		commentary.setContent(content);
		commentary.setItinerary(itinerariesService.findById(itineraryId));
		commentary = commentariesService.save(commentary);
		
		if ( commentary == null ) {
			response.setError("Unable to create commentary");
		} else {
			response.setResult(commentary);
		}
		return response;
	}
}