package com.cooksystems.assessment.team2.api.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksystems.assessment.team2.api.dtos.HashtagDto;
import com.cooksystems.assessment.team2.api.entities.Hashtag;
import com.cooksystems.assessment.team2.api.mappers.HashtagMapper;
import com.cooksystems.assessment.team2.api.repositories.HashtagRepository;
import com.cooksystems.assessment.team2.api.services.HashtagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

	private final HashtagRepository hashtagRepository;
	private final HashtagMapper hashtagMapper;

	@Override
	public List<HashtagDto> getAllHashtags() {
		// TODO Auto-generated method stub
		return hashtagMapper.entitiesToResponseDtos(hashtagRepository.findAll());

	}

}
