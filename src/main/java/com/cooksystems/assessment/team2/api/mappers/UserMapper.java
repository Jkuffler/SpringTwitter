package com.cooksystems.assessment.team2.api.mappers;

import java.util.List;

import com.cooksystems.assessment.team2.api.dtos.UserResponseDto;
import com.cooksystems.assessment.team2.api.entities.User;

public interface UserMapper {

	List<UserResponseDto> entitiesToResponseDtos(List<User> users);

}
