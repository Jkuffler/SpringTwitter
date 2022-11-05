package com.cooksystems.assessment.team2.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksystems.assessment.team2.api.dtos.HashtagDto;
import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.services.HashtagService;


import com.cooksystems.assessment.team2.api.services.TweetService;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("tags")
public class HashtagController {


	@Autowired
	private final HashtagService hashtagService;

	@GetMapping
	public List<HashtagDto> getAllHashtags() {
		return hashtagService.getAllHashtags();
	};

	@GetMapping("/{label}")
	public List<TweetResponseDto> getgetAllHashtagsByLabel(@PathVariable String label) {
		return hashtagService.getAllTweetsByHashtag(label);
	}

	
	@Autowired
	private HashtagService hashtagService;
	
	private TweetService tweetService;
	
	@GetMapping
	public List<HashtagDto> getAllHashtags() {
		return hashtagService.getAllHashtags();
	}
	
	/*
	 * Retrieves all (non-deleted) tweets tagged with the given hashtag label. The tweets should appear in reverse-chronological order. 
	 * If no hashtag with the given label exists, an error should be sent in lieu of a response.
	 * 
	 */
//	@GetMapping("/{label}")
//	public List<HashtagDto> getAllTweetsByTag(String label) {
//		return hashtagService.getAllTweetsByTag(label);
//	}
	
	
	


}
