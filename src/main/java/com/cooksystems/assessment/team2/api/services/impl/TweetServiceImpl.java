package com.cooksystems.assessment.team2.api.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksystems.assessment.team2.api.dtos.TweetRequestDto;
import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.dtos.UserResponseDto;
import com.cooksystems.assessment.team2.api.entities.Credentials;
import com.cooksystems.assessment.team2.api.entities.Tweet;
import com.cooksystems.assessment.team2.api.entities.User;
import com.cooksystems.assessment.team2.api.exceptions.NotAuthorizedException;
import com.cooksystems.assessment.team2.api.exceptions.NotFoundException;
import com.cooksystems.assessment.team2.api.mappers.TweetMapper;
import com.cooksystems.assessment.team2.api.mappers.UserMapper;
import com.cooksystems.assessment.team2.api.repositories.TweetRepository;
import com.cooksystems.assessment.team2.api.repositories.UserRepository;
import com.cooksystems.assessment.team2.api.services.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

	private final TweetRepository tweetRepository;
	private final TweetMapper tweetMapper;
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	private Tweet findTweet(Long id) {
		Optional<Tweet> optionalTweet = tweetRepository.findByIdAndDeletedFalse(id);

		if (optionalTweet.isEmpty()) {
			throw new NotFoundException("No tweet is under the id: " + id);
		}

		return optionalTweet.get();
	}

	private User findUser(String username) {
		Optional<User> optionalUser = userRepository.findByCredentialsUserNameAndDeletedFalse(username);

		if (optionalUser.isEmpty()) {
			throw new NotFoundException("No user is under the username: " + username);
		}

		return optionalUser.get();
	}
	
	private void checkCredentials(Credentials credentials) {
		User user = findUser(credentials.getUserName());

		if (!user.getCredentials().equals(credentials)) {
			throw new NotAuthorizedException("Invalid credentials: " + credentials);
		}
	}

	@Override
	public TweetResponseDto newTweet(TweetRequestDto tweetRequestDto) {
		Tweet tweetToCreate = tweetMapper.tweetRequestDtoToEntity(tweetRequestDto);
		User findAuthor = findUser(tweetRequestDto.getCredentials().getUserName());
		tweetToCreate.setAuthor(findAuthor);
		tweetToCreate.setContent(tweetRequestDto.getContent());

		return tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweetToCreate));
	}

	@Override
	public TweetResponseDto getTweetById(Long id) {
		return tweetMapper.entityToDto(findTweet(id));
	}

	@Override
	public List<TweetResponseDto> getAllTweets() {
		List<Tweet> listOfTweets = tweetRepository.findAllByDeletedFalse();
		Collections.sort(listOfTweets);
		Collections.reverse(listOfTweets);
		return tweetMapper.entitiesToResponseDtos(listOfTweets);
	}

	@Override
	public TweetResponseDto deleteTweet(Long id, Credentials credentials) {
		Tweet tweetToDelete = findTweet(id);
		
		checkCredentials(credentials);

		tweetToDelete.setDeleted(true);
		return tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweetToDelete));
	}
	
	@Override
	public List<UserResponseDto> getTweetLikesById(Long id) {
		Tweet tweetToFind = findTweet(id);
		List<User> likedBy = tweetToFind.getLikedByUsers();

		return userMapper.entitiesToResponseDtos(likedBy);
	}
	
	@Override
	public void likedTweet(Long id, Credentials credentials) {
		Tweet tweetToLike = findTweet(id);
		checkCredentials(credentials);

		List<User> tweetLikes = tweetToLike.getLikedByUsers();
		User userToAdd = findUser(credentials.getUserName());
		
		if(!tweetLikes.contains(userToAdd)) {
			tweetLikes.add(userToAdd);
		}
		
		tweetRepository.saveAndFlush(tweetToLike);
	}
	

}
