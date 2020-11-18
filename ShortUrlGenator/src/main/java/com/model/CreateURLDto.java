package com.model;

public class CreateURLDto {
	private String url;
	private String user;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "URLDto [url=" + url + ", user=" + user + "]";
	}	
}
