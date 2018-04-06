package com.ingesup.java.carhibou.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ingesup.java.carhibou.data.dto.LoginRequestDTO;
import com.ingesup.java.carhibou.data.entities.User;
import com.ingesup.java.carhibou.models.ApiResponse;
import com.ingesup.java.carhibou.models.UserSession;
import com.ingesup.java.carhibou.services.UsersService;

@CrossOrigin("*")
@RestController
@RequestMapping("/session")
public class SessionController {
	
	@Autowired
	private UsersService usersService;

	@PostMapping
	@ResponseBody
	public ApiResponse authenticate( // Connexion d'un user, donc création de session
			@RequestBody LoginRequestDTO loginRequest
	) {
		ApiResponse response = new ApiResponse();
		
		if ( usersService.authenticate(loginRequest.getUsername(), loginRequest.getPassword()) ) {
			User u = usersService.findByUsername(loginRequest.getUsername());
			UserSession session = new UserSession(u);
			response.setResult(session);
		} else {
			response.setError("Wrong username or password");
		}
		
		return response;
	}
	
	@DeleteMapping
	public ApiResponse logout( // Déconnexion d'un user, donc suppression de sa session
		@RequestHeader(value = "Authorization", defaultValue = "") String token
	) {
		ApiResponse result = new ApiResponse();
		
		if ( token.equals("") ) {
			
		}
		User u = usersService.findByToken(token);
		
		u.setToken("");
		u = usersService.save(u);
		return result;
	}
}
