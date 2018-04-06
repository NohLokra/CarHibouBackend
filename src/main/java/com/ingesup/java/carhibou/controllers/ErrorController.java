package com.ingesup.java.carhibou.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/error")
@CrossOrigin("*")
public class ErrorController {
	@RequestMapping(method=RequestMethod.GET)
	public Object index(Object test) {
		System.out.println(test);
		return test;
	}
}
