package com.ingesup.java.carhibou.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ingesup.java.carhibou.data.entities.User;
import com.ingesup.java.carhibou.models.ApiResponse;
import com.ingesup.java.carhibou.services.UsersService;

@RestController
@RequestMapping(value="/notifications")
@CrossOrigin(origins = "*")
public class NotificationsController {
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ApiResponse getForUser(
		@RequestHeader("Authorization") String token
	) {
		ApiResponse result = new ApiResponse();
		
		if ( token == null ) {
			result.setError("Vous devez être connecté pour ccéder à cette fonctionnalité");
			
			return result;
		}
		
		User u = usersService.findByToken(token);
		
		result.setResult(u.getNotifications());
		
		return result;
	}
	
}
