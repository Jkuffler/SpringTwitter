package com.cooksystems.assessment.team2.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("validate")
public class ValidateController {

//	@Autowired
//	private final ValidateService validateService;
//
//	@GetMapping("/username/exists/@{username}")
//	public boolean checkIfUserNameExists(@PathVariable String username) {
//
//		return validateService.checkIfUserNameExists(username);
//
//	}
//
//	@GetMapping("/username/available/@{username}")
//	public boolean checkIfUserNameAvaiable(@PathVariable String username) {
//		if (checkIfUserNameExists(username) == true) {
//			return false;
//		}
//
//		return true;
//
//	}
//	
//	@GetMapping("/tag/exists/{label}")
//	public boolean checkIfTagExists (Hashtag hashtag) {
//		return false;
//		
//	}
	
	

}
