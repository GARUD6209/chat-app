package com.garud.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.garud.chatapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
   
	User findByPhoneNumber(String phoneNumber);
}
