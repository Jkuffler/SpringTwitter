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
	
	@DeleteMapping("/{id}")
	public TweetResponseDto deleteTweet(@PathVariable Long id, @RequestBody Credentials credentials ) {
		return tweetService.deleteTweet(id, credentials);
	}
	
	@GetMapping("/{id}/likes")
	public List<UserResponseDto> getTweetLikesById(@PathVariable Long id){
		return tweetService.getTweetLikesById(id);
	}
	
	@PostMapping("/{id}/like")
	public void likedTweet(@PathVariable Long id, @RequestBody Credentials credentials) {
		tweetService.likedTweet(id, credentials);
	}
	
	@PostMapping("/{id}/repost")
	public TweetResponseDto repostTweet(@PathVariable Long id, @RequestBody Credentials credentials) {
		return tweetService.repostTweet(id, credentials);
	}
	
	@GetMapping("/{id}/reposts")
	public List<TweetResponseDto> getReposts(@PathVariable Long id) {
		return tweetService.getReposts(id);
	}
	
	@PostMapping("/{id}/reply")
	public TweetResponseDto tweetReply(@PathVariable Long id, @RequestBody TweetRequestDto tweetRequestDto) {
		return tweetService.tweetReply(id, tweetRequestDto);
	}
	
	@GetMapping("/{id}/replies")
	public List<TweetResponseDto> getTweetReplies(@PathVariable Long id) {
		return tweetService.getTweetReplies(id);
	}
	
	


}
