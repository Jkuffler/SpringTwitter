package com.cooksystems.assessment.team2.api.services;

import java.util.List;

import com.cooksystems.assessment.team2.api.dtos.TweetRequestDto;
import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.dtos.UserResponseDto;
import com.cooksystems.assessment.team2.api.entities.Credentials;

public interface TweetService {

	TweetResponseDto newTweet(TweetRequestDto tweetRequestDto);

	TweetResponseDto getTweetById(Long id);

	List<TweetResponseDto> getAllTweets();

	TweetResponseDto deleteTweet(Long id, Credentials credentials);

	void likedTweet(Long id, Credentials credentials);

	List<UserResponseDto> getTweetLikesById(Long id);

	TweetResponseDto repostTweet(Long id, Credentials credentials);

	List<TweetResponseDto> getReposts(Long id);

	TweetResponseDto tweetReply(Long id, TweetRequestDto tweetRequestDto);

	List<TweetResponseDto> getTweetReplies(Long id);

}
