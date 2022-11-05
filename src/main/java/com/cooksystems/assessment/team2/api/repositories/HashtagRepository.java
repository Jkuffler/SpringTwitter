package com.cooksystems.assessment.team2.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksystems.assessment.team2.api.entities.Hashtag;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

	Optional<Hashtag> findByLabel(String label);

	List<Hashtag> findAll();

}
