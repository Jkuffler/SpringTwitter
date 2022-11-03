package com.cooksystems.assessment.team2.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooksystems.assessment.team2.api.dtos.UserRequestDto;
import com.cooksystems.assessment.team2.api.dtos.UserResponseDto;
import com.cooksystems.assessment.team2.api.entities.Credentials;
import com.cooksystems.assessment.team2.api.entities.User;


@Mapper(componentModel = "spring", uses = { ProfileMapper.class, CredentialsMapper.class} )
public interface UserMapper {

	List<UserResponseDto> entitiesToResponseDtos(List<User> findAll);
	
	User userRequestDtoToEntity(UserRequestDto userRequestDto);

	@Mapping(source = "credentials.userName", target = "userName")
	UserResponseDto entityToDto(User user);
	

	

}
