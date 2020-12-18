package com.stackroute.service;

import java.util.List;

import com.stackroute.exception.RecommendationBookAlreadyExistsException;
import com.stackroute.exception.RecommendationBookNotFoundException;
import com.stackroute.model.RecommendationBook;

public interface RecommendationBookService {

	boolean deleteRecommendationBookById(int id) throws RecommendationBookNotFoundException;

	RecommendationBook getRecommendationBookById(int id) throws RecommendationBookNotFoundException;

	List<RecommendationBook> getMyRecommendationBooks(String userId);

	boolean saveRecommendationBook(RecommendationBook recommendationbook) throws RecommendationBookAlreadyExistsException;

	List<RecommendationBook> getAllRecommendationBooks();


}
