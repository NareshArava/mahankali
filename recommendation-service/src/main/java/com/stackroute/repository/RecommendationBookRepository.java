package com.stackroute.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.model.RecommendationBook;


@Repository
public interface RecommendationBookRepository extends MongoRepository<RecommendationBook,Integer> {
	
	List<RecommendationBook> findByUserId(String userId);
	List<RecommendationBook> findAll();
	Optional<RecommendationBook> findById(long id);
	
}

