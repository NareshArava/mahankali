package com.stackroute.exception;

@SuppressWarnings("serial")
public class FavouriteBookAlreadyExistsException extends Exception {

	public FavouriteBookAlreadyExistsException(String message) {
		super(message);
	}

}
