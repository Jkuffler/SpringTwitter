package com.cooksystems.assessment.team2.api.services;

import java.util.List;

import com.cooksystems.assessment.team2.api.dtos.TweetRequestDto;
import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;

public interface TweetService {

	TweetResponseDto newTweet(TweetRequestDto tweetRequestDto);

	TweetResponseDto getTweetById(Long id);

	List<TweetResponseDto> getAllTweets();

}
