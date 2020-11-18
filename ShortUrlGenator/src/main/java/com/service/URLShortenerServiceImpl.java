package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.URL;
import com.model.User;
import com.repository.URLRepository;

import java.util.Optional;

@Component
public class URLShortenerServiceImpl implements URLShortenerService {
    @Autowired
    private URLRepository urlRepository;
    
    @Autowired
    private UserService userService;

    @Override
    public Iterable<URL> listURLs(String userName) {
    	User user = userService.getUser(userName);
        return urlRepository.findByUser(user);
    }

    @Override
    public URL addURL(String userName, String url) {
    	if(!userService.isExists(userName)) {
    		userService.saveUser(userName);
    	}
    	
    	URL urlDto = new URL();
    	urlDto.setUrl(url);
    	urlDto.setUser(userService.getUser(userName));
    	urlRepository.save(urlDto);
    	
        return urlDto;
    }

    @Override
    public Optional<URL> getURL(User user, String id) {
        return urlRepository.findByIdAndUser(id, user);
    }

    @Override
    public Optional<URL> getURL(String id) {
        return urlRepository.findById(id);
    }

    @Override
    public void deleteURL(User user, String id) {
        Optional<URL> url = urlRepository.findByIdAndUser(id, user);
        if(url.isPresent()) {
            urlRepository.delete(url.get());
        }
    }

	@Override
	public boolean isURLAndUserExists(User user, String url) {
		Optional<String> id = getId(url);
		if(id.isPresent()) {
			Optional<URL> urlDB = urlRepository.findByIdAndUser(id.get(), user);
	        if(urlDB.isPresent())
	        	return true;
	        else 
	        	return false;
		}else
        	return false;
	}

	@Override
	public Optional<String> getId(String url) {
		if(urlRepository.findByUrl(url).isPresent())
			return Optional.of(urlRepository.findByUrl(url).get().getId());
		
		return Optional.empty();
	}
}
