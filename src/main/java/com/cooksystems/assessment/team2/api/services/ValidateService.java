package com.cooksystems.assessment.team2.api.services;

public interface ValidateService {

	
	boolean checkIfTagExists(String label);

	boolean checkIfUserNameExists(String username);

	boolean checkIfUserNameAvailable(String username);

	

}
