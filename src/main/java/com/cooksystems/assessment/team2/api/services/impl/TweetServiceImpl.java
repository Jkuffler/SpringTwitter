package com.cooksystems.assessment.team2.api.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksystems.assessment.team2.api.dtos.ContextDto;
import com.cooksystems.assessment.team2.api.dtos.HashtagDto;
import com.cooksystems.assessment.team2.api.dtos.TweetRequestDto;
import com.cooksystems.assessment.team2.api.dtos.TweetResponseDto;
import com.cooksystems.assessment.team2.api.dtos.UserResponseDto;
import com.cooksystems.assessment.team2.api.entities.Credentials;
import com.cooksystems.assessment.team2.api.entities.Hashtag;
import com.cooksystems.assessment.team2.api.entities.Tweet;
import com.cooksystems.assessment.team2.api.entities.User;
import com.cooksystems.assessment.team2.api.exceptions.BadRequestException;
import com.cooksystems.assessment.team2.api.exceptions.NotAuthorizedException;
import com.cooksystems.assessment.team2.api.exceptions.NotFoundException;
import com.cooksystems.assessment.team2.api.mappers.HashtagMapper;
import com.cooksystems.assessment.team2.api.mappers.TweetMapper;
import com.cooksystems.assessment.team2.api.mappers.UserMapper;
import com.cooksystems.assessment.team2.api.repositories.TweetRepository;
import com.cooksystems.assessment.team2.api.repositories.UserRepository;
import com.cooksystems.assessment.team2.api.services.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

	private final TweetRepository tweetRepository;
	private final TweetMapper tweetMapper;
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final HashtagMapper hashTagMapper;

	private Tweet findTweet(Long id) {
		Optional<Tweet> optionalTweet = tweetRepository.findByIdAndDeletedFalse(id);

		if (optionalTweet.isEmpty()) {
			throw new NotFoundException("No tweet is under the id: " + id);
		}

		return optionalTweet.get();
	}

	private User findUser(String username) {
		Optional<User> optionalUser = userRepository.findByCredentialsUserNameAndDeletedFalse(username);

		if (optionalUser.isEmpty()) {
			throw new NotFoundException("No user is under the username: " + username);
		}

		return optionalUser.get();
	}
	
	private void checkCredentials(Credentials credentials) {
		User user = findUser(credentials.getUserName());

		if (!user.getCredentials().equals(credentials)) {
			throw new NotAuthorizedException("Invalid credentials: " + credentials);
		}
	}
	
	private void checkTweetRequest(TweetRequestDto tweetRequestDto) {

		if (tweetRequestDto.getCredentials() == null || tweetRequestDto.getCredentials().getUserName() == null
				|| tweetRequestDto.getCredentials().getPassword() == null || tweetRequestDto.getContent() == null) {
			throw new BadRequestException("Something you entered is null, try again.");
		}

	}

	@Override
	public TweetResponseDto postNewTweet(TweetRequestDto tweetRequestDto) {
		Tweet tweetToCreate = tweetMapper.tweetRequestDtoToEntity(tweetRequestDto);
		User findAuthor = findUser(tweetRequestDto.getCredentials().getUserName());
		tweetToCreate.setAuthor(findAuthor);
		tweetToCreate.setContent(tweetRequestDto.getContent());

		return tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweetToCreate));
	}

	@Override
	public TweetResponseDto getTweetById(Long id) {
		return tweetMapper.entityToDto(findTweet(id));
	}

	@Override
	public List<TweetResponseDto> getAllTweets() {
		List<Tweet> listOfTweets = tweetRepository.findAllByDeletedFalse();
		Collections.sort(listOfTweets);
		Collections.reverse(listOfTweets);
		return tweetMapper.entitiesToResponseDtos(listOfTweets);
	}

	@Override
	public TweetResponseDto deleteTweet(Long id, Credentials credentials) {
		Tweet tweetToDelete = findTweet(id);
		
		checkCredentials(credentials);

		tweetToDelete.setDeleted(true);
		return tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweetToDelete));
	}
	
	@Override
	public List<UserResponseDto> getTweetLikesById(Long id) {
		Tweet tweetToFind = findTweet(id);
		List<User> likedBy = tweetToFind.getLikedByUsers();

		return userMapper.entitiesToResponseDtos(likedBy);
	}
	
	@Override
	public void likedTweet(Long id, Credentials credentials) {
		Tweet tweetToLike = findTweet(id);
		checkCredentials(credentials);

		List<User> tweetLikes = tweetToLike.getLikedByUsers();
		User userToAdd = findUser(credentials.getUserName());
		
		if(!tweetLikes.contains(userToAdd)) {
			tweetLikes.add(userToAdd);
		}
		
		tweetRepository.saveAndFlush(tweetToLike);
	}

	@Override
	public TweetResponseDto repostTweet(Long id, Credentials credentials) {
		Tweet tweetToRepost = findTweet(id);
		TweetRequestDto tweetRequest = tweetMapper.entityToRequestDto(tweetToRepost);
		checkCredentials(credentials);
		Tweet repostedTweet = tweetMapper.tweetRequestDtoToEntity(tweetRequest);

		User user = findUser(credentials.getUserName());
		repostedTweet.setAuthor(user);
		repostedTweet.setContent(tweetToRepost.getContent());
		repostedTweet.setRepostOf(tweetToRepost);

		return tweetMapper.entityToDto(tweetRepository.saveAndFlush(repostedTweet));
	}
	
	@Override
	public List<TweetResponseDto> getReposts(Long id) {
		Tweet tweetToFind = findTweet(id); 
		List<Tweet> repostsToTweet = tweetToFind.getReposts();
		
		return tweetMapper.entitiesToResponseDtos(repostsToTweet);
	}
	
	@Override
	public TweetResponseDto tweetReply(Long id, TweetRequestDto tweetRequestDto) {
		Tweet tweetToReplyTo = findTweet(id);

		checkTweetRequest(tweetRequestDto);
		
		User user = findUser(tweetRequestDto.getCredentials().getUserName());
		Tweet replyTweet = tweetMapper.tweetRequestDtoToEntity(tweetRequestDto);
		List<Tweet> tweetReplies = tweetToReplyTo.getReplies();
		
		replyTweet.setAuthor(user);
		replyTweet.setInReplyTo(tweetToReplyTo);
		tweetReplies.add(replyTweet);

		return tweetMapper.entityToDto(tweetRepository.saveAndFlush(replyTweet));

	}
	
	public List<TweetResponseDto> getTweetReplies(Long id) {
		Tweet tweetToFind = findTweet(id);
		List<Tweet> repliesToTweet = tweetToFind.getReplies();
		
		return tweetMapper.entitiesToResponseDtos(repliesToTweet);
	}
	
	@Override
	public List<UserResponseDto> getTweetMentions(Long id) {
		Tweet tweetToFind = findTweet(id);
		List<User> mentionedUsers = tweetToFind.getMentionedUsers();
			
		return userMapper.entitiesToResponseDtos(mentionedUsers);
	}
	
	@Override
	public ContextDto getTweetContext(Long id) {
		Tweet tweetToFind = findTweet(id);
		ContextDto context = new ContextDto();
		List<TweetResponseDto> tweetList = new ArrayList<>();
		Tweet currentTweet = tweetToFind;
		
		context.setTarget(tweetMapper.entityToDto(tweetToFind));
		context.setAfter(tweetMapper.entitiesToResponseDtos(tweetToFind.getReplies()));
		
		while(currentTweet.getInReplyTo() != null) {
			tweetList.add(tweetMapper.entityToDto(currentTweet.getInReplyTo()));
			currentTweet = currentTweet.getInReplyTo();
		}
		
		context.setBefore(tweetList);
		
		return context;
	}
	
	@Override
	public List<HashtagDto> getHashtagsByTweetId(Long id) {
		Tweet tweetToFind = findTweet(id);
		List<Hashtag> hashtagList = tweetToFind.getHashtags();
			
		return hashTagMapper.entitiesToResponseDtos(hashtagList);
	}

	
	

}
