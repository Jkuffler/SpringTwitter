package com.cooksystems.assessment.team2.api.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TweetMapper {

}
