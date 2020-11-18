package com.service;

import java.sql.Timestamp;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.URLStatistic;
import com.repository.URLStatisticsRepository;

@Component
public class URLStatisticServiceImpl implements URLStatisticService{

	@Autowired
	private URLShortenerService urlShortenerService;
	
	@Autowired 
	private URLStatisticsRepository repository; 
	
	@Override
	public Optional<URLStatistic> getUrlStatistic(String urlId) {
		if(urlShortenerService.getURL(urlId).isPresent()) {
			return repository.findByUrl(urlShortenerService.getURL(urlId).get());
		}
		return Optional.empty();
	}

	@Override
	public void save(URLStatistic urlStatistic) {
		repository.save(urlStatistic);
	}

	@Override
	public void update(URLStatistic urlStatistic, String referer, String userAgent) {
		Optional<URLStatistic> urlStatisticDb = repository.findByUrl(urlStatistic.getUrl());
		if(urlStatisticDb.isPresent()) {
			URLStatistic urlStatisticUpdate = urlStatisticDb.get();
			urlStatisticUpdate.setAccessAt(new Timestamp(System.currentTimeMillis()));
			urlStatisticUpdate.setNumberOfCalls(urlStatisticUpdate.getNumberOfCalls()+1);
			urlStatisticUpdate.setReferrer(referer);
			urlStatisticUpdate.setUserAgent(userAgent);
			repository.save(urlStatisticUpdate);
		}
	}

}




















