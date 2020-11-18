package com.model;

public class URLAndIdDto {
	private String url;
	private String id;
	
	public URLAndIdDto(String url, String id) {
		super();
		this.url = url;
		this.id = id;
	}
	
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
	
	@Override
	public String toString() {
		return "URLAndId [url=" + url + ", id=" + id + "]";
	}
}
