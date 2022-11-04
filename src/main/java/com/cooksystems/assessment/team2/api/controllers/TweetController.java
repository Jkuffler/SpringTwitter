package com.cooksystems.assessment.team2.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksystems.assessment.team2.api.dtos.TweetRequestDto;
import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.services.TweetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("tweets")
public class TweetController {
	
	@Autowired
	final private TweetService tweetService;
	
	@GetMapping
	public List<TweetResponseDto> getAllTweets(){
		return tweetService.getAllTweets();
		
	}
	
	@GetMapping("/{id}")
	public TweetResponseDto getTweetById(@PathVariable Long id) {
		return tweetService.getTweetById(id);
	}

	@PostMapping
	public TweetResponseDto newTweet(@RequestBody TweetRequestDto tweetRequestDto) {
		return tweetService.newTweet(tweetRequestDto);
	}

}
