package com.cooksystems.assessment.team2.api.services;

import com.cooksystems.assessment.team2.api.dtos.TweetRequestDto;
import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;

public interface TweetService {

	TweetResponseDto newTweet(TweetRequestDto tweetRequestDto);

	TweetResponseDto getTweetById(Long id);

}
