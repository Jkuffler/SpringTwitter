package com.cooksystems.assessment.team2.api.mappers;

import org.mapstruct.Mapper;

import com.cooksystems.assessment.team2.api.dtos.CredentialsDto;
import com.cooksystems.assessment.team2.api.entities.Credentials;


@Mapper(componentModel = "spring")
public interface CredentialsMapper {
	
	CredentialsDto entityToDto(Credentials entity);
	
	//tracked but not stashed or committed yet
	Credentials credentialsDtoToEntity(CredentialsDto credentialsDto);
	
	
}
