package com.cooksystems.assessment.team2.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksystems.assessment.team2.api.dtos.ContextDto;
import com.cooksystems.assessment.team2.api.dtos.HashtagDto;
import com.cooksystems.assessment.team2.api.dtos.TweetRequestDto;
import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.dtos.UserResponseDto;
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
	@ResponseStatus(HttpStatus.OK)
	public List<TweetResponseDto> getAllTweets(){
		return tweetService.getAllTweets();
		
	}
	
	/*
	 * Retrieves a tweet with a given id. 
	 * If no such tweet exists, or the given tweet is deleted, an error should be sent in lieu of a response.
	 */
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TweetResponseDto getTweetById(@PathVariable Long id) {
		return tweetService.getTweetById(id);
	}
	
	/*
	 * Creates a new simple tweet, with the author set to the user identified by the credentials in the request body. 
	 * If the given credentials do not match an active user in the database, an error should be sent in lieu of a response.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TweetResponseDto postNewTweet(@RequestBody TweetRequestDto tweetRequestDto) {
		return tweetService.postNewTweet(tweetRequestDto);
	}
	
	/*
	 * "Deletes" the tweet with the given id. If no such tweet exists or the provided credentials do not match author of the tweet, an error should be sent in lieu of a response. 
	 * If a tweet is successfully "deleted", the response should contain the tweet data prior to deletion.
	 * IMPORTANT: This action should not actually drop any records from the database! 
	 * Instead, develop a way to keep track of "deleted" tweets so that even if a tweet is deleted, 
	 * data with relationships to it (like replies and reposts) are still intact.
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TweetResponseDto deleteTweet(@PathVariable Long id, @RequestBody Credentials credentials ) {
		return tweetService.deleteTweet(id, credentials);
	}
	
	@GetMapping("/{id}/likes")
	@ResponseStatus(HttpStatus.OK)
	public List<UserResponseDto> getTweetLikesById(@PathVariable Long id){
		return tweetService.getTweetLikesById(id);
	}
	
	@PostMapping("/{id}/like")
	@ResponseStatus(HttpStatus.CREATED)
	public void likedTweet(@PathVariable Long id, @RequestBody Credentials credentials) {
		tweetService.likedTweet(id, credentials);
	}
	
	@PostMapping("/{id}/repost")
	@ResponseStatus(HttpStatus.CREATED)
	public TweetResponseDto repostTweet(@PathVariable Long id, @RequestBody Credentials credentials) {
		return tweetService.repostTweet(id, credentials);
	}
	
	@GetMapping("/{id}/reposts")
	@ResponseStatus(HttpStatus.OK)
	public List<TweetResponseDto> getReposts(@PathVariable Long id) {
		return tweetService.getReposts(id);
	}
	
	@PostMapping("/{id}/reply")
	@ResponseStatus(HttpStatus.CREATED)
	public TweetResponseDto tweetReply(@PathVariable Long id, @RequestBody TweetRequestDto tweetRequestDto) {
		return tweetService.tweetReply(id, tweetRequestDto);
	}
	
	@GetMapping("/{id}/replies")
	@ResponseStatus(HttpStatus.OK)
	public List<TweetResponseDto> getTweetReplies(@PathVariable Long id) {
		return tweetService.getTweetReplies(id);
	}
	
	@GetMapping("/{id}/mentions")
	@ResponseStatus(HttpStatus.OK)
	public List<UserResponseDto> getTweetMentions(@PathVariable Long id){
		return tweetService.getTweetMentions(id);
	}
	
	@GetMapping("/{id}/context")
	@ResponseStatus(HttpStatus.OK)
	public ContextDto getTweetContext(@PathVariable Long id) {
		return tweetService.getTweetContext(id);
	}
	
	@GetMapping("/{id}/tags")
	@ResponseStatus(HttpStatus.OK)
	public List<HashtagDto> getTagsById(@PathVariable Long id) {
		return tweetService.getTagsById(id);
	}
	
	


}
