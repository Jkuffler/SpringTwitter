package com.cooksystems.assessment.team2.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksystems.assessment.team2.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {
	
	private UserService userService;

}
