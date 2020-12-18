package com.stackroute.service;

import java.util.Map;

import com.stackroute.domain.User;

public interface SecurityTokenGenerator {
	
	Map<String,String> generateToken(User user);
}
