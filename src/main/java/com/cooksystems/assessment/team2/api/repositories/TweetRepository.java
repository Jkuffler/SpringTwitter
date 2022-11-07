package com.cooksystems.assessment.team2.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksystems.assessment.team2.api.entities.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

	Optional<Tweet> findByIdAndDeletedFalse(Long id);

	List<Tweet> findAllByDeletedFalse();


}
