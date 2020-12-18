package com.stackroute.repository;


import java.util.*;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.model.FavouriteBook;
@Repository
public interface FavouriteBookRepository extends MongoRepository<FavouriteBook,Integer> {
	
	List<FavouriteBook> findByUserId(String userId);

	Optional<FavouriteBook> findById(long id);
}
