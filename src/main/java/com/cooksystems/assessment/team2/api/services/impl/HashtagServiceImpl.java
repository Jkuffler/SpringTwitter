package com.cooksystems.assessment.team2.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksystems.assessment.team2.api.dtos.HashtagDto;
import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.entities.Hashtag;
import com.cooksystems.assessment.team2.api.exceptions.NotFoundException;
import com.cooksystems.assessment.team2.api.mappers.HashtagMapper;
import com.cooksystems.assessment.team2.api.mappers.TweetMapper;
import com.cooksystems.assessment.team2.api.repositories.HashtagRepository;
import com.cooksystems.assessment.team2.api.services.HashtagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

	private final HashtagRepository hashtagRepository;
	private final HashtagMapper hashtagMapper;
	private final TweetMapper tweetMapper;

	@Override
	public List<HashtagDto> getAllHashtags() {

		return hashtagMapper.entitiesToResponseDtos(hashtagRepository.findAll());
	};

	@Override
	public List<TweetResponseDto> getAllTweetsByHashtag(String label) {

		Optional<Hashtag> optionalHashtag = hashtagRepository.findByLabel(label);
		if (optionalHashtag.isEmpty()) {
			throw new NotFoundException("Tag not found");
		}
		return tweetMapper.entitiesToResponseDtos(optionalHashtag.get().getTweets());
	};

}
