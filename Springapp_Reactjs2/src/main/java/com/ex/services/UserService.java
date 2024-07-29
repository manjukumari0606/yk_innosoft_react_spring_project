package com.ex.services;

import com.ex.entity.User_Info;

public interface UserService {
	
	User_Info findByEmail(String email);
	User_Info saveUser(User_Info user_Info);

}
