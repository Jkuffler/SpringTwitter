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
	 * Retrieves the active users who have liked the tweet with the given id. 
	 * If that tweet is deleted or otherwise doesn't exist, an error should be sent in lieu of a response.
	 * Deleted users should be excluded from the response.
	 */
	@GetMapping("/{id}/likes")
	public List<UserResponseDto> getTweetLikesById(@PathVariable Long id){
		return tweetService.getTweetLikesById(id);
	}
	
	/*
	 * Creates a new simple tweet, with the author set to the user identified by the credentials in the request body. 
	 * If the given credentials do not match an active user in the database, an error should be sent in lieu of a response.
	 */
	@PostMapping
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
	public TweetResponseDto deleteTweet(@PathVariable Long id, @RequestBody Credentials credentials ) {
		return tweetService.deleteTweet(id, credentials);
	}
	
	/*
	 * Creates a "like" relationship between the tweet with the given id and the user whose credentials are provided by the request body. 
	 * If the tweet is deleted or otherwise doesn't exist, or if the given credentials do not match an active user in the database, an error should be sent. 
	 * Following successful completion of the operation, no response body is sent.
	 */
	@PostMapping("/{id}/like")
	public void likedTweet(@PathVariable Long id, @RequestBody Credentials credentials) {
		tweetService.likedTweet(id, credentials);
	}
	/*
	 * Creates a repost of the tweet with the given id. The author of the repost should match the credentials provided in the request body. 
	 * If the given tweet is deleted or otherwise doesn't exist, or the given credentials do not match an active user in the database, an error should be sent in lieu of a response.
	 * Because this creates a repost tweet, content is not allowed. Additionally, notice that the repostOf property is not provided by the request. 
	 * The server must create that relationship.
	 * The response should contain the newly-created tweet.
	 */
	@PostMapping("/{id}/repost")
	public TweetResponseDto repostTweet(@PathVariable Long id, @RequestBody Credentials credentials) {
		return tweetService.repostTweet(id, credentials);
	}
	
	/*
	 * Retrieves the direct reposts of the tweet with the given id. 
	 * If that tweet is deleted or otherwise doesn't exist, an error should be sent in lieu of a response.
	 */
	@GetMapping("/{id}/reposts")
	public List<TweetResponseDto> getReposts(@PathVariable Long id) {
		return tweetService.getReposts(id);
	}
	
	/*
	 * Creates a reply tweet to the tweet with the given id. The author of the newly-created tweet should match the credentials provided by the request body. 
	 * If the given tweet is deleted or otherwise doesn't exist, or if the given credentials do not match an active user in the database, an error should be sent in lieu of a response.
	 * Because this creates a reply tweet, content is not optional. 
	 * Additionally, notice that the inReplyTo property is not provided by the request. The server must create that relationship.
	 * The response should contain the newly-created tweet.
	 */
	@PostMapping("/{id}/reply")
	public TweetResponseDto tweetReply(@PathVariable Long id, @RequestBody TweetRequestDto tweetRequestDto) {
		return tweetService.tweetReply(id, tweetRequestDto);
	}
	
	/*
	 * Retrieves the direct replies to the tweet with the given id. 
	 * If that tweet is deleted or otherwise doesn't exist, an error should be sent in lieu of a response.
	 * Deleted replies to the tweet should be excluded from the response.
	 */
	@GetMapping("/{id}/replies")
	public List<TweetResponseDto> getTweetReplies(@PathVariable Long id) {
		return tweetService.getTweetReplies(id);
	}
	
	/*
	 * Retrieves the users mentioned in the tweet with the given id. 
	 * If that tweet is deleted or otherwise doesn't exist, an error should be sent in lieu of a response.
	 * Deleted users should be excluded from the response.
	 * IMPORTANT Remember that tags and mentions must be parsed by the server!
	 */
	@GetMapping("/{id}/mentions")
	public List<UserResponseDto> getTweetMentions(@PathVariable Long id){
		return tweetService.getTweetMentions(id);
	}
	
	/*
	 * Retrieves the context of the tweet with the given id. 
	 * If that tweet is deleted or otherwise doesn't exist, an error should be sent in lieu of a response.
	 * IMPORTANT: While deleted tweets should not be included in the before and after properties of the result, transitive replies should. 
	 * What that means is that if a reply to the target of the context is deleted, but there's another reply to the deleted reply, the deleted reply should be excluded but the other reply should remain.
	 */
	@GetMapping("/{id}/context")
	public ContextDto getTweetContext(@PathVariable Long id) {
		return tweetService.getTweetContext(id);
	}
	
	@GetMapping("/{id}/tags")
	public List<HashtagDto> getHashtagsByTweetId(@PathVariable Long id) {
		return tweetService.getHashtagsByTweetId(id);
	}
	
	


}
