package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.entity.UserDto;
import com.example.demo.entity.UserEm;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/password")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/loginUser")
	public ResponseEntity<String> loginUser(@RequestBody User user) {
		userService.userLogin(user);
		return new ResponseEntity<>("user registered", HttpStatus.OK);
	}

	@GetMapping("/forgotpass")
	public ResponseEntity<String> forgotpass(@RequestParam String email ) {
		userService.forgotPass(email);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	@PostMapping("/resetPass")
	public ResponseEntity<User> resetPass(@RequestBody User user) {
		return new ResponseEntity<>(userService.ResetPassword(user), HttpStatus.OK);

	}

}
