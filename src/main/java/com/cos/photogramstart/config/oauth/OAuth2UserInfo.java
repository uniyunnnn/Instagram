package com.cos.photogramstart.config.oauth;

import java.util.Map;

import javax.persistence.Column;

public abstract class OAuth2UserInfo {

	   protected Map<String, Object> attributes;

	    public OAuth2UserInfo(Map<String, Object> attributes) {
	        this.attributes = attributes;
	    }

	    public Map<String, Object> getAttributes() {
	        return attributes;
	    }

	    public abstract String getUsername();

	    public abstract String getId();

	    public abstract String getName();

	    @Column(nullable = false)
	    public abstract String getEmail();

	    public abstract String getImageUrl();
}
