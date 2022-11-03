package com.cooksystems.assessment.team2.api.mappers;

import org.mapstruct.Mapper;

import com.cooksystems.assessment.team2.api.dtos.ProfileDto;
import com.cooksystems.assessment.team2.api.entities.Profile;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
	
	ProfileDto entityToProfileDto (Profile profile);
	
	Profile profileDtoToEntity (ProfileDto profileDto);

}
