package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.model.URLErrorResponse;
import com.model.URLStatistic;
import com.model.URLStatisticResponse;
import com.service.URLStatisticService;


@RestController
public class URLStatisticController {

	@Autowired
	private URLStatisticService urlStatisticService;
	
	@GetMapping(path="/geturlstatistic/{urlId}")
	public ResponseEntity<?> getUrlStatistic(@PathVariable String urlId){
		if(urlStatisticService.getUrlStatistic(urlId).isPresent()) {
			URLStatistic urlStatistic = urlStatisticService.getUrlStatistic(urlId).get();
			URLStatisticResponse urlStatisticResponse = new URLStatisticResponse();
			urlStatisticResponse.setUrlId(urlId);
			urlStatisticResponse.setNumberOfCalls(urlStatistic.getNumberOfCalls());
			urlStatisticResponse.setAccessAt(urlStatistic.getAccessAt());
			urlStatisticResponse.setReferrer(urlStatistic.getReferrer());
			urlStatisticResponse.setUserAgent(urlStatistic.getUserAgent());
			return new ResponseEntity<URLStatisticResponse>(urlStatisticResponse, HttpStatus.OK);
		}else {
			URLErrorResponse urlErrorResponse = new URLErrorResponse();
			urlErrorResponse.setStatus("404");
			urlErrorResponse.setError("Your given URLId does not found in our system.");
			return new ResponseEntity<URLErrorResponse>(urlErrorResponse, HttpStatus.OK);
		}
	}
}
