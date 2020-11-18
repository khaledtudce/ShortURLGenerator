package com.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "url_statistic")
public class URLStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

	@Column(name = "number_of_calls")
    private int numberOfCalls;

    @Column(name = "access_at")
    private Timestamp accessAt;
    
    @Column(name = "referrer")
    private String referrer;
    
    @Column(name = "user_agent")
    private String userAgent;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="url_id", referencedColumnName = "id")
    private URL url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}
    
}
