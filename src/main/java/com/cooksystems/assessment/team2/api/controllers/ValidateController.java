package com.cooksystems.assessment.team2.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksystems.assessment.team2.api.services.ValidateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {
<<<<<<< HEAD
	
=======

	private final ValidateService validateService;

	/*
	 * Checks whether or not a given username exists.
	 */
	@GetMapping("/username/exists/@{username}")
	public boolean checkIfUserNameExists(@PathVariable String username) {
		return validateService.checkIfUserNameExists(username);

	}

	/*
	 * Checks whether or not a given username is available.
	 */
	@GetMapping("/username/available/@{username}")
	public boolean checkIfUserNameAvaiable(@PathVariable String username) {
		return validateService.checkIfUserNameAvailable(username);

	}

	/*
	 * Checks whether or not a given hashtag exists.
	 */
	@GetMapping("/tag/exists/{label}")
	public boolean checkIfTagExists(@PathVariable String label) {
		return validateService.checkIfTagExists(label);

	}

>>>>>>> 12c803c75d65c733b21646216d37f0a06b81b3ef
}
