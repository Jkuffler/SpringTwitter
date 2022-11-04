package com.cooksystems.assessment.team2.api.mappers;

import org.mapstruct.Mapper;

import com.cooksystems.assessment.team2.api.dtos.TweetRequestDto;
import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.entities.Tweet;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TweetMapper {

	TweetResponseDto entityToDto(Tweet entity);
	
	TweetRequestDto entityToRequestDto(Tweet entity);
	
	Tweet tweetRequestDtoToEntity(TweetRequestDto tweetRequesetDto);
}
