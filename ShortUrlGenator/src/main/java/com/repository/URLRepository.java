package com.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.URL;
import com.model.User;

@Repository
public interface URLRepository extends CrudRepository<URL, String> {
    Iterable<URL> findByUser(User user);
    Optional<URL> findByIdAndUser(String id, User user);
    boolean existsByUrl(String url);
    Optional<URL> findByUrl(String url);
}
