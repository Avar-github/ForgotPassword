package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserDto;
import com.example.demo.entity.UserEm;

@Service
public interface UserService {
	
	
	//adding user to databse
	void userLogin(User user);
	
	//forgot pass
	void forgotPass(String email);

	// reset password
	User ResetPassword(User user);
}
