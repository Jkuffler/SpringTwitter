package com.cooksystems.assessment.team2.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksystems.assessment.team2.api.dtos.HashtagDto;
import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.services.HashtagService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("tags")
public class HashtagController {


	@Autowired
	private final HashtagService hashtagService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<HashtagDto> getAllHashtags() {
		return hashtagService.getAllHashtags();
	};

	@GetMapping("/{label}")
	public List<TweetResponseDto> getAllTweetsByHashtag(@PathVariable String label) {
		return hashtagService.getAllTweetsByHashtag(label);
	}
	


}
