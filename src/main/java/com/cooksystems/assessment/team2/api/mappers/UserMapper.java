package com.cooksystems.assessment.team2.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksystems.assessment.team2.api.dtos.UserResponseDto;
import com.cooksystems.assessment.team2.api.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	List<UserResponseDto> entitiesToResponseDtos(List<User> findAll);

	

}
