package com.cooksystems.assessment.team2.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksystems.assessment.team2.model.UserResponseDto;

@Service
public interface UserService {

	public List<UserResponseDto> getAllUsers();

}
