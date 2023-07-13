package com.example.demo.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.UUID;

import org.apache.naming.java.javaURLContextFactory;
import org.hibernate.annotations.common.reflection.java.JavaReflectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.UserDtoManRepo;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.entity.UserDto;
import com.example.demo.entity.UserDtoManager;
import com.example.demo.entity.UserEm;
import com.example.demo.exception.UserNotFoundException;

import lombok.Builder;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserDtoManRepo userDtoManRepo;
	@Autowired 
	JavaMailSender mailSender;

	@Override
	public void userLogin(User user) {
		BCryptPasswordEncoder bCryptPassword=new BCryptPasswordEncoder();
	    String string=	bCryptPassword.encode(user.getPassword());
	    user.setPassword(string);
		userRepository.save(user);
	}

	@Override
	public User ResetPassword(User user) {
		LocalTime currentTime=LocalTime.now();
		String email=user.getEmail();
		String otp=user.getOtp();
		String newpass=user.getPassword();
		User user2=userRepository.findByEmail(email);
		String opt2=user2.getOtp();
		LocalTime timeString=user2.getLocalTime();
		long diff=ChronoUnit.MINUTES.between(currentTime, timeString);
		if(otp.equals(opt2) && diff<5)
		{
			System.out.println("i am in if");
			user2.setPassword(newpass);
			userRepository.save(user2);
			return user2;
		}
		else if(!otp.equals(opt2)){
			throw new UserNotFoundException("Your OPT is wrong");
		}
		else {
			throw new UserNotFoundException("Your session is time out");

		}
	}

	@Override
	public void forgotPass(String email) {
		User user= userRepository.findByEmail(email);
		if(user==null)
		{
			throw new UserNotFoundException("User Not Found");
		}
		else {
			user.setPassword(null);
			int min=1000;
			int max=10000;
			Integer randomInteger=(int)(Math.random()*(max-min+1))+min;
			SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
			simpleMailMessage.setFrom("avar.mittal@bonamisoftware.com");
			simpleMailMessage.setTo("avarmittal1sep@gmail.com");
			simpleMailMessage.setText(randomInteger.toString());
			simpleMailMessage.setSubject("Bhool Gya Password");
			mailSender.send(simpleMailMessage);
			user.setLocalTime(LocalTime.now());
			user.setOtp(randomInteger.toString());
			userRepository.save(user);
		}
	}
}


