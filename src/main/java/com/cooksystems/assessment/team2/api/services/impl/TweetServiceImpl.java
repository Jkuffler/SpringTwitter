package com.cooksystems.assessment.team2.api.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksystems.assessment.team2.api.dtos.TweetRequestDto;
import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.entities.Tweet;
import com.cooksystems.assessment.team2.api.entities.User;
import com.cooksystems.assessment.team2.api.exceptions.NotFoundException;
import com.cooksystems.assessment.team2.api.mappers.TweetMapper;
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

}
