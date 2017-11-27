package edu.sjsu.cmpe275.lab2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@RequestMapping("/")
	public String Hello() {
		return "Hello World!";
	}

}
