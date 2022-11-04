package com.cooksystems.assessment.team2.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksystems.assessment.team2.api.dtos.HashtagDto;
import com.cooksystems.assessment.team2.api.entities.Hashtag;

@Mapper(componentModel = "spring")
public interface HashtagMapper {

	List<HashtagDto> entitiesToResponseDtos(List<Hashtag> hashtagList);

}
