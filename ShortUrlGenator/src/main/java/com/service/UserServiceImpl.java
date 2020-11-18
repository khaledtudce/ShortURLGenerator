package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.User;
import com.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public User getUser(String userName) {
		return repository.findByUser(userName);
	}

	@Override
	public void setUser(User user) {
		repository.save(user);
	}

	@Override
	public boolean isExists(String userName) {
		return repository.existsByUser(userName);
	}

	@Override
	public void saveUser(String userName) {
		User userDTO = new User();
		userDTO.setUser(userName);
		repository.save(userDTO);
	}

}
