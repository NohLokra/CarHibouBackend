package com.ingesup.java.carhibou.controllers;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ingesup.java.carhibou.models.ApiResponse;
import com.ingesup.java.carhibou.models.UserSession;
import com.ingesup.java.carhibou.data.entities.User;
import com.ingesup.java.carhibou.services.UsersService;

import java.util.regex.Matcher;

@RestController
@RequestMapping(value="/users")
@CrossOrigin(origins = "*")
public class UsersController {
	
	@Autowired
	UsersService usersService;
	
	@RequestMapping(value="/auth", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse authenticate(
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
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ApiResponse register(
		@RequestParam("username") String username,
		@RequestParam("password") String password,
		@RequestParam("email") String email,
		@RequestParam("passwordConfirm") String passwordConfirm
	) {
		ApiResponse response = new ApiResponse();
		
		if ( !password.equals(passwordConfirm) ) {
			response.setError("Passwords don't match");
			
			return response;
		}
		
		Pattern mailPattern = Pattern.compile("/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$/", Pattern.CASE_INSENSITIVE);
		Matcher m = mailPattern.matcher(email);
		
		if ( !m.find() ) {
			response.setError("Invalid Email");
		}
			
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setEmail(email);
		
		u = usersService.create(u);
		
		UserSession session = new UserSession(u);
		response.setResult(session);
		
		return response;
	}
	
	@RequestMapping(value="/auth", method=RequestMethod.DELETE)
	public ApiResponse logout(
		@RequestHeader("Authorization") String authorization
	) {
		ApiResponse result = new ApiResponse();
		
		System.out.println(authorization);
		
		return result;
	}
}
