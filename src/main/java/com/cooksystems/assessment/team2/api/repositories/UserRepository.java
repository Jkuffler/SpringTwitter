package com.cooksystems.assessment.team2.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksystems.assessment.team2.api.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByIdLong(Long id);
	
	

}
