package com.model;

public class DeleteURLDto {
	private String user;
	private String urlId;
	
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
	
	@Override
	public String toString() {
		return "UserAndURLId [user=" + user + ", urlId=" + urlId + "]";
	}
}
