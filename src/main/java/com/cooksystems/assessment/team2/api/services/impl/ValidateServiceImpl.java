package com.cooksystems.assessment.team2.api.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksystems.assessment.team2.api.entities.Hashtag;
import com.cooksystems.assessment.team2.api.entities.User;
import com.cooksystems.assessment.team2.api.repositories.HashtagRepository;
import com.cooksystems.assessment.team2.api.repositories.UserRepository;
import com.cooksystems.assessment.team2.api.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

	private final HashtagRepository hashtagRepository;

	private final UserRepository userRepository;

	
	@Override
	public boolean checkIfTagExists(String label) {
		List<Hashtag> listOfHashTags = hashtagRepository.findAll();
		for (Hashtag hashtag : listOfHashTags) {
			if (hashtag.getLabel().equals("#" + label)) {
				return true;
			}

		}
		return false;
	}

	
	@Override
	public boolean checkIfUserNameAvailable(String username) {
		List<User> listOfUsers = userRepository.findAll();
		for (User user : listOfUsers) {
			if (user.getCredentials().getUserName().equals(username)) {
				System.out.println("Username " + username + " is not available.");
				return false;
			}
		}
		return true;
	}

	
	@Override
	public boolean checkIfUserNameExists(String username) {
		List<User> listOfUsers = userRepository.findAll();
		for (User user : listOfUsers) {
			if (user.getCredentials().getUserName().equals(username)) {
				return true;
			}
		}
		return false;
	}

}
