package com.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.model.URLStatistic;

@Service
public interface URLStatisticService {
	Optional<URLStatistic> getUrlStatistic(String urlId);
	void save(URLStatistic urlStatistic);
	void update(URLStatistic urlStatistic, String referer, String userAgent);
}
