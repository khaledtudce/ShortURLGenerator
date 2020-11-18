package com.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.CreateURLDto;
import com.model.DeleteURLDto;
import com.model.URL;
import com.model.URLAndIdDto;
import com.model.URLCreateResponse;
import com.model.URLDeleteResponse;
import com.model.URLErrorResponse;
import com.model.URLListResponse;
import com.model.URLStatistic;
import com.model.User;
import com.service.URLShortenerService;
import com.service.URLStatisticService;
import com.service.UserService;

@RestController
public class URLShortenerController {

	@Autowired
	private URLShortenerService urlShortenerService; 
	
	@Autowired
	private URLStatisticService urlStatisticService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addurl")
	public ResponseEntity<?> addURL(@RequestBody CreateURLDto urlDto){
		if(urlShortenerService.isURLAndUserExists(userService.getUser(urlDto.getUser()), urlDto.getUrl())) {
			URLErrorResponse urlErrorResponse = new URLErrorResponse();
			urlErrorResponse.setStatus("409");
			urlErrorResponse.setError("Conflict! Your given URL is already exists with following id: " + urlShortenerService.getId(urlDto.getUrl()));
			return new ResponseEntity<URLErrorResponse>(urlErrorResponse, HttpStatus.CONFLICT);
		}else {
			URL createdUrl = urlShortenerService.addURL(urlDto.getUser(), urlDto.getUrl());
			URLCreateResponse urlResponse = new URLCreateResponse();
			urlResponse.setId(createdUrl.getId());
			urlResponse.setUrl(createdUrl.getUrl());
			urlResponse.setMessage("Successfully genereated short url. You can use this short url by this id now by http://localhost:8080/id");
			return new ResponseEntity<URLCreateResponse>(urlResponse, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/deleteurl")
	public ResponseEntity<?> deleteUrl(@RequestBody DeleteURLDto deleteUrlDto){
		if(urlShortenerService.getURL(deleteUrlDto.getUrlId()).isPresent()) {
			User user = userService.getUser(deleteUrlDto.getUser());
			urlShortenerService.deleteURL(user, deleteUrlDto.getUrlId());
			URLDeleteResponse urlResponse = new URLDeleteResponse();
			urlResponse.setUrlId(deleteUrlDto.getUrlId());;
			urlResponse.setUser(deleteUrlDto.getUser());;
			urlResponse.setMessage("Successfully deleted your given URL.");
			return new ResponseEntity<URLDeleteResponse>(urlResponse, HttpStatus.OK);
		}else {
			URLErrorResponse urlErrorResponse = new URLErrorResponse();
			urlErrorResponse.setStatus("404");
			urlErrorResponse.setError("Your given User or URL Id is/are not found in our system.");
			return new ResponseEntity<URLErrorResponse>(urlErrorResponse, HttpStatus.OK);
		}
	}
	
	@GetMapping("/{urlId}")
	public ResponseEntity<?> redirectToOriginalURL(@PathVariable String urlId, HttpServletRequest req, HttpServletResponse response) throws IOException{
		if(urlShortenerService.getURL(urlId).isPresent()) {
			URL url = urlShortenerService.getURL(urlId).get();
			saveUrlStatistic(url, req);
			response.sendRedirect(url.getUrl());
		}else {
			URLErrorResponse urlErrorResponse = new URLErrorResponse();
			urlErrorResponse.setStatus("404");
			urlErrorResponse.setError("Your given URL Id is not found in our system.");
			return new ResponseEntity<URLErrorResponse>(urlErrorResponse, HttpStatus.OK);
		}
		
		return null;
	}
	
	@RequestMapping("/geturls/{user}")
	public ResponseEntity<?> getUrls(@PathVariable String user){
		Iterable<URL> listUrls = urlShortenerService.listURLs(user);
		List<URLAndIdDto> urlAndIds = new ArrayList<URLAndIdDto>();
		for (URL url : listUrls)
			urlAndIds.add(new URLAndIdDto(url.getUrl(), url.getId()));
		
		URLListResponse urlListResponse = new URLListResponse();
		urlListResponse.setMessage("List of URL and corresponding Id for your given user. You can use them by these ids now by following way, http://localhost:8080/id");
		urlListResponse.setUrlAndIds(urlAndIds);
		
	    return new ResponseEntity<URLListResponse>(urlListResponse, HttpStatus.OK);
	}
	
	private void saveUrlStatistic(URL url, HttpServletRequest req) {
		String referer = req.getHeader("referrer");
		String userAgent = req.getHeader("User-Agent");
		
		if(urlStatisticService.getUrlStatistic(url.getId()).isPresent()) {
			URLStatistic urlStatistic = new URLStatistic();
			urlStatistic.setUrl(url);
			urlStatisticService.update(urlStatistic, referer, userAgent);
		}else {
			URLStatistic urlStatistic = new URLStatistic();
			urlStatistic.setUrl(url);
			urlStatistic.setNumberOfCalls(1);
			urlStatistic.setAccessAt(new Timestamp(System.currentTimeMillis()));
			urlStatistic.setReferrer(referer);
			urlStatistic.setUserAgent(userAgent);
			urlStatisticService.save(urlStatistic);
		}
	}
}

















