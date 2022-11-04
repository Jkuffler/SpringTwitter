package com.cooksystems.assessment.team2.api.services;

import java.util.List;

import com.cooksystems.assessment.team2.api.dtos.UserRequestDto;
import com.cooksystems.assessment.team2.api.dtos.UserResponseDto;
import com.cooksystems.assessment.team2.api.entities.Credentials;

public interface UserService {

	List<UserResponseDto> getAllUsers();
	
	UserResponseDto createUser(UserRequestDto userRequestDto);

	UserResponseDto updateUser(String username, UserRequestDto userRequestDto);

	UserResponseDto deleteUser(String username, Credentials credentials);

	UserResponseDto getUserByUserName(String userName);

}
