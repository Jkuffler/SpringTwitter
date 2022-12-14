package com.cooksystems.assessment.team2.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.cooksystems.assessment.team2.api.dtos.UserRequestDto;
import com.cooksystems.assessment.team2.api.dtos.UserResponseDto;
import com.cooksystems.assessment.team2.api.entities.User;

@Mapper(componentModel = "spring", uses = { ProfileMapper.class, CredentialsMapper.class })
public interface UserMapper {

	List<UserResponseDto> entitiesToResponseDtos(List<User> findAll);

	@Autowired
	User userRequestDtoToEntity(UserRequestDto userRequestDto);

	@Mapping(source = "credentials.username", target = "username")
	UserResponseDto entityToDto(User user);

}
