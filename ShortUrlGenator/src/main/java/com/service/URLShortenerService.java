package com.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.model.URL;
import com.model.User;

@Service
public interface URLShortenerService {
    Iterable<URL> listURLs(String userName);
    URL addURL(String userName, String url);
    Optional<URL> getURL(User user, String id);
    Optional<URL> getURL(String id);
    boolean isURLAndUserExists(User user, String url);
    void deleteURL(User user, String id);
    Optional<String> getId(String url);
}
