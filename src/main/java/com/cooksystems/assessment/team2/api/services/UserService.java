package com.cooksystems.assessment.team2.api.services;

import java.util.List;

import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.dtos.UserRequestDto;
import com.cooksystems.assessment.team2.api.dtos.UserResponseDto;
import com.cooksystems.assessment.team2.api.entities.Credentials;

public interface UserService {

	List<UserResponseDto> getAllUsers();

	UserResponseDto createUser(UserRequestDto userRequestDto);

	UserResponseDto updateUserByUsername(String username, UserRequestDto userRequestDto);

	UserResponseDto deleteUserByUsername(String username, Credentials credentials);

	UserResponseDto getUserByUserName(String userName);

	List<TweetResponseDto> getTweetsbyAuthor(String username);

	void follow(String username, Credentials credentials);

	void unfollow(String username, Credentials credentials);

	List<UserResponseDto> getFollowers(String username);

	List<TweetResponseDto> getFeedByAuthor(String username);

	List<UserResponseDto> getFollowing(String username);

	List<TweetResponseDto> getUserMentions(String username);

}
