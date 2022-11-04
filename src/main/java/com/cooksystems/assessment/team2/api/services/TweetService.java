package com.cooksystems.assessment.team2.api.services;

import java.util.List;

import com.cooksystems.assessment.team2.api.dtos.TweetRequestDto;
import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.entities.Credentials;

public interface TweetService {

	TweetResponseDto newTweet(TweetRequestDto tweetRequestDto);

	TweetResponseDto getTweetById(Long id);

	List<TweetResponseDto> getAllTweets();

	TweetResponseDto deleteTweet(Long id, Credentials credentials);

}
