package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, String>{
	User findByUser(String user);
	boolean existsByUser(String user);
}
