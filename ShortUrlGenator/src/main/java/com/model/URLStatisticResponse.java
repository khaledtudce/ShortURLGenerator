package com.model;

import java.sql.Timestamp;

public class URLStatisticResponse {
	private String urlId;
	private int numberOfCalls;
	private Timestamp accessAt;
	private String referrer;
	private String userAgent;
	
	public String getUrlId() {
		return urlId;
	}
	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}
	public int getNumberOfCalls() {
		return numberOfCalls;
	}
	public void setNumberOfCalls(int numberOfCalls) {
		this.numberOfCalls = numberOfCalls;
	}
	public Timestamp getAccessAt() {
		return accessAt;
	}
	public void setAccessAt(Timestamp accessAt) {
		this.accessAt = accessAt;
	}
	public String getReferrer() {
		return referrer;
	}
	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	@Override
	public String toString() {
		return "URLStatisticResponse [urlId=" + urlId + ", numberOfCalls=" + numberOfCalls + ", accessAt=" + accessAt
				+ ", referrer=" + referrer + ", userAgent=" + userAgent + "]";
	}
}
