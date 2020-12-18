package com.stackroute.service;

import java.util.List;

import com.stackroute.exception.FavouriteBookAlreadyExistsException;
import com.stackroute.exception.FavouriteBookNotFoundException;
import com.stackroute.model.FavouriteBook;

public interface FavouriteBookService {

	boolean saveFavouriteBook(FavouriteBook favouritebook) throws FavouriteBookAlreadyExistsException;

	boolean deleteFavouriteBookById(int id) throws FavouriteBookNotFoundException;

	FavouriteBook getFavouriteBookById(int id) throws FavouriteBookNotFoundException;

	List<FavouriteBook> getMyFavouriteBooks(String userId);



}

