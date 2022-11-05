package com.cooksystems.assessment.team2.api.services;

import java.util.List;

import com.cooksystems.assessment.team2.api.dtos.HashtagDto;

import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;



public interface HashtagService {

	List<HashtagDto> getAllHashtags();

	List<TweetResponseDto> getAllTweetsByHashtag(String label);

}
