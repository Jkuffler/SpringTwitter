package com.cooksystems.assessment.team2.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksystems.assessment.team2.api.dtos.TweetRequestDto;
import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.entities.Credentials;
import com.cooksystems.assessment.team2.api.services.TweetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("tweets")
public class TweetController {
	
	@Autowired
	final private TweetService tweetService;
	
	/*
	 * Retrieves all (non-deleted) tweets. The tweets should appear in reverse-chronological order.
	 */
	@GetMapping
	public List<TweetResponseDto> getAllTweets(){
		return tweetService.getAllTweets();
		
	}
	
	/*
	 * Retrieves a tweet with a given id. 
	 * If no such tweet exists, or the given tweet is deleted, an error should be sent in lieu of a response.
	 */
	@GetMapping("/{id}")
	public TweetResponseDto getTweetById(@PathVariable Long id) {
		return tweetService.getTweetById(id);
	}
	
	/*
	 * Creates a new simple tweet, with the author set to the user identified by the credentials in the request body. 
	 * If the given credentials do not match an active user in the database, an error should be sent in lieu of a response.
	 */
	@PostMapping
	public TweetResponseDto newTweet(@RequestBody TweetRequestDto tweetRequestDto) {
		return tweetService.newTweet(tweetRequestDto);
	}
	
	/*
	 * "Deletes" the tweet with the given id. If no such tweet exists or the provided credentials do not match author of the tweet, an error should be sent in lieu of a response. 
	 * If a tweet is successfully "deleted", the response should contain the tweet data prior to deletion.
	 * IMPORTANT: This action should not actually drop any records from the database! 
	 * Instead, develop a way to keep track of "deleted" tweets so that even if a tweet is deleted, 
	 * data with relationships to it (like replies and reposts) are still intact.
	 */
	@DeleteMapping("/{id}")
	public TweetResponseDto deleteTweet(@PathVariable Long id, @RequestBody Credentials credentials ) {
		return tweetService.deleteTweet(id, credentials);
	}

}
