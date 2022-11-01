package com.cooksystems.assessment.team2.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksystems.assessment.team2.api.dtos.UserResponseDto;

@Service
public interface UserService {

	public List<UserResponseDto> getAllUsers();

}
