package com.model;

public class URLCreateResponse {
	private String url;
	private String id;
	private String message;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "URLResponse [url=" + url + ", id=" + id + ", message=" + message + "]";
	}
	
}
