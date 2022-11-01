package com.cooksystems.assessment.team2.mappers;

import java.util.List;

import com.cooksystems.assessment.team2.entities.User;
import com.cooksystems.assessment.team2.model.UserResponseDto;

public interface UserMapper {

	List<UserResponseDto> entitiesToResponseDtos(List<User> users);

}
