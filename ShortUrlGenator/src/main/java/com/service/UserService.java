package com.service;

import org.springframework.stereotype.Service;

import com.model.User;

@Service
public interface UserService {
	User getUser(String userName);
	void setUser(User user);
	boolean isExists(String userName);
	void saveUser(String userName);
}
