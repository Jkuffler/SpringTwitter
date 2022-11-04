package com.cooksystems.assessment.team2.api.services;

import java.util.List;

import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.dtos.UserRequestDto;
import com.cooksystems.assessment.team2.api.dtos.UserResponseDto;
import com.cooksystems.assessment.team2.api.entities.Credentials;
import com.cooksystems.assessment.team2.api.entities.Tweet;

public interface UserService {

	List<UserResponseDto> getAllUsers();

	UserResponseDto createUser(UserRequestDto userRequestDto);

	UserResponseDto updateUser(String username, UserRequestDto userRequestDto);

	UserResponseDto deleteUser(String username, Credentials credentials);

	UserResponseDto getUserByUserName(String userName);

	List<TweetResponseDto> getTweetsbyAuthor(String userName);

	void follow(String username, Credentials credentials);

	void unfollow(String username, Credentials credentials);

	List<UserResponseDto> followers(String username);

	List<UserResponseDto> following(String username);

}
