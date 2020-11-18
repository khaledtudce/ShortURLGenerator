package com.model;

public class URLDeleteResponse {
	private String user;
	private String urlId;
	private String message;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUrlId() {
		return urlId;
	}
	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "URLDeleteResponse [user=" + user + ", urlId=" + urlId + ", message=" + message + "]";
	}
}
