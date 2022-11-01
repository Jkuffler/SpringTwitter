package com.cooksystems.assessment.team2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cooksystems.assessment.team2.services.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("user")
public class UserController {
	
	private final UserService userService;

}
