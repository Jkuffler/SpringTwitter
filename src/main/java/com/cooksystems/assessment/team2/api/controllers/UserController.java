package com.cooksystems.assessment.team2.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.dtos.UserRequestDto;
import com.cooksystems.assessment.team2.api.dtos.UserResponseDto;
import com.cooksystems.assessment.team2.api.entities.Credentials;
import com.cooksystems.assessment.team2.api.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	/*
	 * Retrieves all active (non-deleted) users as an array.
	 */
	@GetMapping
	public List<UserResponseDto> getAllUsers() {
		return userService.getAllUsers();
	}

	/*
	 * Retrieves a user with the given username. If no such user exists or is
	 * deleted, an error should be sent in lieu of a response.
	 */
	@GetMapping("/@{username}")
	public UserResponseDto getUserByUserName(@PathVariable String username) {
		return userService.getUserByUserName(username);
	}

	/*
	 * Retrieves all (non-deleted) tweets authored by the user with the given
	 * username. This includes simple tweets, reposts, and replies. The tweets
	 * should appear in reverse-chronological order. If no active user with that
	 * username exists (deleted or never created), an error should be sent in lieu
	 * of a response.
	 */
	@GetMapping("/@{username}/tweets")
	public List<TweetResponseDto> getTweetsByAuthor(@PathVariable String username) {
		return userService.getTweetsbyAuthor(username);

	}

	/*
	 * Retrieves all (non-deleted) tweets authored by the user with the given
	 * username, as well as all (non-deleted) tweets authored by users the given
	 * user is following. This includes simple tweets, reposts, and replies. The
	 * tweets should appear in reverse-chronological order. If no active user with
	 * that username exists (deleted or never created), an error should be sent in
	 * lieu of a response.
	 */
	@GetMapping("/@{username}/feed")
	public List<TweetResponseDto> getFeedByAuthor(@PathVariable String username) {
		return userService.getFeedByAuthor(username);

	}

	/*
	 * Retrieves the users followed by the user with the given username. 
	 * Only active users should be included in the response. 
	 * If no active user with the given username exists, an error should be sent in lieu of a response.
	 */
	@GetMapping("/@{username}/following")
	public List<UserResponseDto> getFollowing(@PathVariable String username) {
		return userService.getFollowing(username);

	}

	/*
	 * Retrieves the followers of the user with the given username. 
	 * Only active users should be included in the response. 
	 * If no active user with the given username exists, an error should be sent in lieu of a response.
	 */
	@GetMapping("/@{username}/followers")
	public List<UserResponseDto> getFollowers(@PathVariable String username) {
		return userService.getFollowers(username);
	}

	/*
	 * Creates a new user. If any required fields are missing or the username
	 * provided is already taken, an error should be sent in lieu of a response. If
	 * the given credentials match a previously-deleted user, re-activate the
	 * deleted user instead of creating a new one.
	 */
	@PostMapping
	public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
		return userService.createUser(userRequestDto);
	}


	
	/*
	 * Subscribes the user whose credentials are provided by the request body to the user whose username is given in the url. 
	 * If there is already a following relationship between the two users, no such followable user exists (deleted or never created), 
	 * or the credentials provided do not match an active user in the database, an error should be sent as a response. 
	 * If successful, no data is sent.
	 */
	@PostMapping("/@{username}/follow")
	public void follow(@PathVariable String username, @RequestBody Credentials credentials) {
		userService.follow(username, credentials);
	}
	
	/*
	 * Unsubscribes the user whose credentials are provided by the request body from the user whose username is given in the url. 
	 * If there is no preexisting following relationship between the two users, no such followable user exists (deleted or never created), 
	 * or the credentials provided do not match an active user in the database, an error should be sent as a response. 
	 * If successful, no data is sent.
	 */
	@PostMapping("/@{username}/unfollow")
	public void unfollow(@PathVariable String username, @RequestBody Credentials credentials) {
		userService.unfollow(username, credentials);
	}
	
	/*
	 * Updates the profile of a user with the given username. If no such user
	 * exists, the user is deleted, or the provided credentials do not match the
	 * user, an error should be sent in lieu of a response. In the case of a
	 * successful update, the returned user should contain the updated data.
	 */
	@PatchMapping("/@{username}")
	public UserResponseDto updateUserByUsername(@PathVariable String username, @RequestBody UserRequestDto userRequestDto) {
		return userService.updateUserByUsername(username, userRequestDto);
	}

	
	/*
	 * "Deletes" a user with the given username. If no such user exists or the
	 * provided credentials do not match the user, an error should be sent in lieu
	 * of a response. If a user is successfully "deleted", the response should
	 * contain the user data prior to deletion.
	 */
	@DeleteMapping("/@{username}")
	public UserResponseDto deleteUserByUsername(@PathVariable String username, @RequestBody Credentials credentials) {
		return userService.deleteUserByUsername(username, credentials);
	}
	
	@GetMapping("/@{username}/mentions")
	public List<TweetResponseDto> getUserMentions(@PathVariable String username) {
		return userService.getUserMentions(username);
	}

}
