package com.ingesup.java.carhibou.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ingesup.java.carhibou.data.entities.User;
import com.ingesup.java.carhibou.models.ApiResponse;
import com.ingesup.java.carhibou.models.UserSession;
import com.ingesup.java.carhibou.services.UsersService;

@RestController
@RequestMapping(value="/session")
@CrossOrigin(origins = "*")
public class SessionController {
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse authenticate( // Connexion d'un user, donc création de session
			@RequestParam("username") String username, 
			@RequestParam("password") String password
	) {
		ApiResponse response = new ApiResponse();
		
		if ( usersService.authenticate(username, password) ) {
			User u = usersService.findByUsername(username);
			UserSession session = new UserSession(u);
			response.setResult(session);
		} else {
			response.setError("Wrong username or password");
		}
		
		return response;
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public ApiResponse logout( // Déconnexion d'un user, donc suppression de sa session
		@RequestHeader("Authorization") String authorization
	) {
		ApiResponse result = new ApiResponse();
		
		User u = usersService.findByToken(authorization);
		
		u.setToken("");
		u = usersService.save(u);
		return result;
	}
}
