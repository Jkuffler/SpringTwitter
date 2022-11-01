package com.cooksystems.assessment.team2.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksystems.assessment.team2.mappers.UserMapper;
import com.cooksystems.assessment.team2.model.UserResponseDto;
import com.cooksystems.assessment.team2.repositories.UserRepository;
import com.cooksystems.assessment.team2.services.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private UserMapper userMapper;

	@Override
	public List<UserResponseDto> getAllUsers() {

		return userMapper.entitiesToResponseDtos(userRepository.findAll());
	}

}
