package com.ex.dto;

import com.ex.entity.User_Info;

public class UserResponse {
	    private String token;
	    private User_Info user;  

	    public UserResponse(String token, User_Info user) {
	        this.token = token;
	        this.user = user;
	    }

	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }

	    public User_Info getUser() {
	        return user;
	    }

	    public void setUser(User_Info user) {
	        this.user = user;
	    }
	}


