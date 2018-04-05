package com.ingesup.java.carhibou.controllers;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ingesup.java.carhibou.models.ApiResponse;
import com.ingesup.java.carhibou.models.UserSession;
import com.ingesup.java.carhibou.data.dto.UserRegistrationDTO;
import com.ingesup.java.carhibou.data.entities.User;
import com.ingesup.java.carhibou.services.UsersService;

import java.util.regex.Matcher;

@RestController
@RequestMapping(value="/users")
@CrossOrigin(origins = "*")
public class UsersController {
	
	@Autowired
	UsersService usersService;
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public ApiResponse register( // Enregistrement d'un user, donc création
		@RequestBody UserRegistrationDTO registration
	) {
		ApiResponse response = new ApiResponse();

		if ( !registration.getPassword().equals(registration.getConfirmPassword()) ) {
			response.setError("Passwords don't match");
			
			return response;
		}
		
		Pattern mailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher m = mailPattern.matcher(registration.getEmail());

		if ( !m.find() ) {
			response.setError("Invalid Email");
			
			return response;
		}
		
		if ( usersService.findByEmail(registration.getEmail()) != null ) {
			response.setError("Email already in use");
			
			return response;
		}
		
		if ( usersService.findByUsername(registration.getUsername().toLowerCase()) != null ) {
			response.setError("Username already in use");
			
			return response;
		}
			
		User u = new User();
		u.setUsername(registration.getUsername().toLowerCase());
		u.setPassword(registration.getPassword());
		u.setEmail(registration.getEmail());
		
		u = usersService.create(u);
		
		UserSession session = new UserSession(u);
		response.setResult(session);
		
		return response;
	}

}
