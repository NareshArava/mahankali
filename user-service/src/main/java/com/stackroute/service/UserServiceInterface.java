package com.stackroute.service;

import java.util.*;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.exception.UserNotFoundException;
public interface UserServiceInterface {
	
	boolean saveUser(User user) throws UserAlreadyExistsException;
	public User findByUserIdAndPassword(String userId,String password)throws UserNotFoundException;

}
