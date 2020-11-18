package com.model;

import java.util.List;

public class URLListResponse {
	
	String message;
	List<URLAndIdDto> urlAndIds;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<URLAndIdDto> getUrlAndIds() {
		return urlAndIds;
	}

	public void setUrlAndIds(List<URLAndIdDto> urlAndIds) {
		this.urlAndIds = urlAndIds;
	}

	@Override
	public String toString() {
		return "URLListResponse [message=" + message + ", urlAndIds=" + urlAndIds + "]";
	}

}
