package com.cooksystems.assessment.team2.api.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.dtos.UserRequestDto;
import com.cooksystems.assessment.team2.api.dtos.UserResponseDto;
import com.cooksystems.assessment.team2.api.entities.Credentials;
import com.cooksystems.assessment.team2.api.entities.Tweet;
import com.cooksystems.assessment.team2.api.entities.User;
import com.cooksystems.assessment.team2.api.exceptions.BadRequestException;
import com.cooksystems.assessment.team2.api.exceptions.NotAuthorizedException;
import com.cooksystems.assessment.team2.api.exceptions.NotFoundException;
import com.cooksystems.assessment.team2.api.mappers.TweetMapper;
import com.cooksystems.assessment.team2.api.mappers.UserMapper;
import com.cooksystems.assessment.team2.api.repositories.TweetRepository;
import com.cooksystems.assessment.team2.api.repositories.UserRepository;
import com.cooksystems.assessment.team2.api.services.UserService;
import com.cooksystems.assessment.team2.api.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	private final TweetRepository tweetRepository;

	private final UserMapper userMapper;

	private final TweetMapper tweetMapper;
	
	private final ValidateService validateService;

	private User findUser(String username) {
		Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(username);

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
		User user = findUser(credentials.getUsername());

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

		if (userRequestDto.getCredentials() == null || userRequestDto.getCredentials().getUsername() == null
				|| userRequestDto.getCredentials().getPassword() == null) {
			throw new BadRequestException("No credentials present.");
		}
		if (userRequestDto.getProfile() == null) {
			throw new NotAuthorizedException("No profile is present");
		}
					
		User savedUser = userMapper.userRequestDtoToEntity(userRequestDto);
		
		List<User> listOfUser = userRepository.findAll();
		for (User user : listOfUser) {
			if (user.getCredentials().getUsername().equals(userRequestDto.getCredentials().getUsername()) &&
					user.isDeleted()) {
				savedUser = user;
				savedUser.setDeleted(false);
				return userMapper.entityToDto(userRepository.saveAndFlush(savedUser));
			}
		}
		
		if (!(validateService.checkIfUserNameAvailable(savedUser.getCredentials().getUsername())) && !savedUser.isDeleted()) {
			throw new NotAuthorizedException("Username not available");
		}
		if (savedUser.getCredentials().getUsername() == null || savedUser.getCredentials().getPassword() == null || 
				savedUser.getProfile().getEmail() == null) {
			throw new BadRequestException("Username, password, and email are required");
			
			
		}

		return userMapper.entityToDto(userRepository.saveAndFlush(savedUser));
	}

	@Override
	public UserResponseDto deleteUserByUsername(String username, Credentials credentials) {
		User userToDelete = findUser(username);

		checkCredentials(credentials);

		userToDelete.setDeleted(true);
		return userMapper.entityToDto(userRepository.saveAndFlush(userToDelete));
	}

//	@Override
//	public UserResponseDto updateUserByUsername(String username, UserRequestDto userRequestDto) {
//		User userToUpdate = findUser(username);
//		User updates = userMapper.userRequestDtoToEntity(userRequestDto);
//
//		if (updates.getProfile() == null || updates.getCredentials() == null) {
//			throw new BadRequestException("Error! Please fill all the required fields.");
//		}
//
//		checkCredentialsDto(username, userRequestDto);
//
//		Profile profile = userToUpdate.getProfile();
//
//		if (updates.getProfile().getEmail() != null) {
//			profile.setEmail(updates.getProfile().getEmail());
//		}
//		if (updates.getProfile().getFirstName() != null) {
//			profile.setFirstName(updates.getProfile().getFirstName());
//		}
//		if (updates.getProfile().getLastName() != null) {
//			profile.setLastName(updates.getProfile().getLastName());
//		}
//		if (updates.getProfile().getPhone() != null) {
//			profile.setPhone(updates.getProfile().getPhone());
//		}
//
////		userToUpdate.setProfile(profile);
//		return userMapper.entityToDto(userRepository.saveAndFlush(userToUpdate));
//	}
	
	@Override
    public UserResponseDto updateUserByUsername(String username, UserRequestDto userRequestDto) {
        if (userRequestDto.getCredentials() == null || userRequestDto.getCredentials().getUsername() == null ||
                 userRequestDto.getCredentials().getPassword() == null) {
            throw new NotAuthorizedException("No credentials present");
        }
        if (userRequestDto.getProfile() == null) {
            throw new NotAuthorizedException("No profile present");
        }
        if (!userRequestDto.getCredentials().getUsername().equals(username))
            throw new BadRequestException("Wrong user to modify or wrong username sent");

        User incomingUser = findUser(username);

        if (!incomingUser.getCredentials().getPassword().equals(userRequestDto.getCredentials().getPassword()))
            throw new NotAuthorizedException("Incorrect password.");

        if (!(userRequestDto.getProfile().getFirstName() == null))
            incomingUser.getProfile().setFirstName(userRequestDto.getProfile().getFirstName());

        if (!(userRequestDto.getProfile().getLastName() == null))
            incomingUser.getProfile().setLastName(userRequestDto.getProfile().getLastName());

        if (!(userRequestDto.getProfile().getEmail() == null))
            incomingUser.getProfile().setEmail(userRequestDto.getProfile().getEmail());

        if (!(userRequestDto.getProfile().getPhone() == null))
            incomingUser.getProfile().setPhone(userRequestDto.getProfile().getPhone());

        return userMapper.entityToDto(userRepository.saveAndFlush(incomingUser));
    }
	

	@Override
	public UserResponseDto getUserByUserName(String username) {
		User getUser = findUser(username);
		return userMapper.entityToDto(getUser);
	}

	@Override
	public List<TweetResponseDto> getTweetsbyAuthor(String username) {
		User user = findUser(username);
		if (user.isDeleted()) {
			throw new BadRequestException(username);
		}
		List<Tweet> listOfTweets = tweetRepository.findAll();
		List<Tweet> userTweets = new ArrayList<>();
		for (Tweet tweet : listOfTweets) {
			if (tweet.getAuthor().getCredentials().getUsername().equalsIgnoreCase(username) && !tweet.isDeleted())  {
				userTweets.add(tweet);
			}
		}
//		Collections.sort(listOfTweets);
//		Collections.reverse(listOfTweets);

		return tweetMapper.entitiesToDto(userTweets);
	}

	@Override
	public List<UserResponseDto> getFollowers(String username) {
//		User user = findUser(username);
//		List<User> followers = user.getFollowers();
//		for (User u : followers) {
//			if (u.isDeleted()) {
//				followers.remove(u);
//			}
//		}
		
		
		return userMapper.entitiesToResponseDtos(findUser(username).getFollowers());
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
		User follower = findUser(credentials.getUsername());
		if (userToFollow.isDeleted()) {
			throw new NotFoundException("User deleted");
		}
		
		if (!userToFollow.getFollowers().contains(follower)) {
			userToFollow.getFollowers().add(follower);
		}else {
			throw new BadRequestException("Already followed!");
			
		}
		if (!follower.getFollowing().contains(userToFollow)) {
			follower.getFollowing().add(userToFollow);
		}else {
			throw new NotAuthorizedException("Already a follower");
		}

		userRepository.saveAllAndFlush(Arrays.asList(follower, userToFollow));

	}

	@Override
	public void unfollow(String username, Credentials credentials) {
		checkCredentials(credentials);
		User userToUnfollow = findUser(username);
		User unfollower = findUser(credentials.getUsername());
		if (userToUnfollow.isDeleted()) {
			throw new NotFoundException("User deleted");
		}
		
		if (userToUnfollow.getFollowers().contains(unfollower)) {
			userToUnfollow.getFollowers().add(unfollower);
		}else {
			throw new BadRequestException("Already followed!");
			
		}
		if (unfollower.getFollowing().contains(userToUnfollow)) {
			unfollower.getFollowing().add(userToUnfollow);
		}else {
			throw new NotAuthorizedException("Already a follower");
		}

		userRepository.saveAllAndFlush(Arrays.asList(unfollower, userToUnfollow));
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
	
	@Override
	public List<TweetResponseDto> getUserMentions(String username) {
		User user = findUser(username);
		List<Tweet> mentions = user.getMentionedTweets();
		Collections.sort(mentions);
		Collections.reverse(mentions);
		return tweetMapper.entitiesToResponseDtos(mentions);
	}

}
