package com.cooksystems.assessment.team2.api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksystems.assessment.team2.api.dtos.HashtagDto;
import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.entities.Tweet;
import com.cooksystems.assessment.team2.api.exceptions.NotFoundException;
import com.cooksystems.assessment.team2.api.mappers.HashtagMapper;
import com.cooksystems.assessment.team2.api.mappers.TweetMapper;
import com.cooksystems.assessment.team2.api.repositories.HashtagRepository;
import com.cooksystems.assessment.team2.api.repositories.TweetRepository;
import com.cooksystems.assessment.team2.api.services.HashtagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

	private final HashtagRepository hashtagRepository;
	private final TweetRepository tweetRepository;
	private final HashtagMapper hashtagMapper;

	private final TweetMapper tweetMapper;

	@Override
	public List<HashtagDto> getAllHashtags() {

		return hashtagMapper.entitiesToResponseDtos(hashtagRepository.findAll());
	};

	@Override
	public List<TweetResponseDto> getAllTweetsByHashtag(String label) {

		List<Tweet> listOfTweets = tweetRepository.findAll();
		List<Tweet> listOfTweetsToReturn = new ArrayList<>();
		for (Tweet tweet : listOfTweets) {
			if (tweet.getContent() == null) {
				continue;
			}

			else if (!tweet.isDeleted() && tweet.getContent().contains("#" + label)) {
				listOfTweetsToReturn.add(tweet);
			}
		}

		if (listOfTweetsToReturn.isEmpty()) {
			throw new NotFoundException("Hashtag not found");
		}
		return tweetMapper.entitiesToResponseDtos(listOfTweetsToReturn);
	};

}
