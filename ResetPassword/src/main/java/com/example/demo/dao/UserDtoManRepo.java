package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserDtoManager;

@Repository
public interface UserDtoManRepo extends JpaRepository<UserDtoManager, Integer>{

	UserDtoManager findByEmail(String email);
	UserDtoManager findByUuid(String uuid);
}
