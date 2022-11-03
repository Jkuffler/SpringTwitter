package com.cooksystems.assessment.team2.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooksystems.assessment.team2.api.dtos.UserResponseDto;
import com.cooksystems.assessment.team2.api.entities.User;


@Mapper(componentModel = "spring", uses = {ProfileMapper.class, CredentialsMapper.class} )
public interface UserMapper {

	@Mapping(target = "userName", source = "credentials.userName")
	UserResponseDto entityToResponseDto(User user);
	
	List<UserResponseDto> entitiesToResponseDtos(List<User> findAll);
	
	

	
	

	


	

}
