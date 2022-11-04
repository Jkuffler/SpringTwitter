package com.cooksystems.assessment.team2.api.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.dtos.UserRequestDto;
import com.cooksystems.assessment.team2.api.dtos.UserResponseDto;
import com.cooksystems.assessment.team2.api.entities.Credentials;
import com.cooksystems.assessment.team2.api.entities.Profile;
import com.cooksystems.assessment.team2.api.entities.Tweet;
import com.cooksystems.assessment.team2.api.entities.User;
import com.cooksystems.assessment.team2.api.exceptions.BadRequestException;
import com.cooksystems.assessment.team2.api.exceptions.NotAuthorizedException;
import com.cooksystems.assessment.team2.api.exceptions.NotFoundException;
import com.cooksystems.assessment.team2.api.mappers.TweetMapper;
import com.cooksystems.assessment.team2.api.mappers.UserMapper;
import com.cooksystems.assessment.team2.api.repositories.UserRepository;
import com.cooksystems.assessment.team2.api.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	private final TweetMapper tweetMapper;

	private User findUser(String username) {
		Optional<User> optionalUser = userRepository.findByCredentialsUserNameAndDeletedFalse(username);

		if (optionalUser.isEmpty()) {
			throw new NotFoundException("No user is under the username: " + username);
		}

		return optionalUser.get();
	}

	private void checkCredentialsDto(String username, UserRequestDto userRequestDto) {
		User verifyUser = findUser(username);
		User verifyCredentials = userMapper.userRequestDtoToEntity(userRequestDto);
		if (!verifyCredentials.getCredentials().equals(verifyUser.getCredentials())) {
			throw new NotAuthorizedException("Invalid credentials: " + userRequestDto);
		}
	}

	private void checkCredentials(Credentials credentials) {
		User user = findUser(credentials.getUserName());

		if (!user.getCredentials().equals(credentials)) {
			throw new NotAuthorizedException("Invalid credentials: " + credentials);
		}
	}

	@Override
	public List<UserResponseDto> getAllUsers() {

		return userMapper.entitiesToResponseDtos(userRepository.findAllByDeletedFalse());
	}

	@Override
	public UserResponseDto createUser(UserRequestDto userRequestDto) {

		User savedUser = userMapper.userRequestDtoToEntity(userRequestDto);
		return userMapper.entityToDto(userRepository.saveAndFlush(savedUser));
	}

	@Override
	public UserResponseDto deleteUser(String username, Credentials credentials) {
		User userToDelete = findUser(username);

		checkCredentials(credentials);

		userToDelete.setDeleted(true);
		return userMapper.entityToDto(userRepository.saveAndFlush(userToDelete));
	}

	@Override
	public UserResponseDto updateUser(String username, UserRequestDto userRequestDto) {
		User userToUpdate = findUser(username);
		User updates = userMapper.userRequestDtoToEntity(userRequestDto);

		if (updates.getProfile() == null || updates.getCredentials() == null) {
			throw new BadRequestException("Error! Please fill all the required fields.");
		}

		checkCredentialsDto(username, userRequestDto);

		Profile profile = userToUpdate.getProfile();

		if (updates.getProfile().getEmail() != null) {
			profile.setEmail(updates.getProfile().getEmail());
		}
		if (updates.getProfile().getFirstName() != null) {
			profile.setFirstName(updates.getProfile().getFirstName());
		}
		if (updates.getProfile().getLastName() != null) {
			profile.setLastName(updates.getProfile().getLastName());
		}
		if (updates.getProfile().getPhone() != null) {
			profile.setPhone(updates.getProfile().getPhone());
		}

		userToUpdate.setProfile(profile);
		return userMapper.entityToDto(userRepository.saveAndFlush(userToUpdate));
	}

	@Override
	public UserResponseDto getUserByUserName(String userName) {

		return userMapper.entityToDto(findUser(userName));
	}

	@Override
	public List<TweetResponseDto> getTweetsbyAuthor(String username) {
		User user = findUser(username);
		List<Tweet> listOfTweets = user.getTweets();
		Collections.sort(listOfTweets);
		Collections.reverse(listOfTweets);

		return tweetMapper.entitiesToDto(listOfTweets);
	}

	@Override
	public List<UserResponseDto> getFollowers(String username) {
		User user = findUser(username);
		return userMapper.entitiesToResponseDtos(user.getFollowers());
	}

	@Override
	public List<UserResponseDto> getFollowing(String username) {
		User user = findUser(username);

		return userMapper.entitiesToResponseDtos(user.getFollowing());
	}

	@Override
	public void follow(String username, Credentials credentials) {
		checkCredentials(credentials);
		User userToFollow = findUser(username);
		User follower = findUser(credentials.getUserName());

		List<User> following = follower.getFollowing();

		if (following.contains(userToFollow)) {
			throw new BadRequestException("You are already following the user.");
		} else {
			following.add(userToFollow);
		}

		follower.setFollowing(following);

		userRepository.saveAndFlush(follower);

	}

	@Override
	public void unfollow(String username, Credentials credentials) {
		checkCredentials(credentials);
		User userToUnfollow = findUser(username);
		User unfollower = findUser(credentials.getUserName());

		List<User> following = unfollower.getFollowing();

		if (following.contains(userToUnfollow)) {
			following.remove(userToUnfollow);
		} else {
			throw new BadRequestException("You are not following the user.");
		}

		unfollower.setFollowing(following);

		userRepository.saveAndFlush(unfollower);

	}

	@Override
	public List<TweetResponseDto> getFeedByAuthor(String username) {
		User user = findUser(username);
		List<Tweet> listOfTweets = user.getTweets();
		for (User user1 : user.getFollowing()) {
			listOfTweets.addAll(user1.getTweets());
		}
		Collections.sort(listOfTweets);
		Collections.reverse(listOfTweets);

		return tweetMapper.entitiesToResponseDtos(listOfTweets);
	}

}
