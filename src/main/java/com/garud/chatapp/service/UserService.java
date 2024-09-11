package com.garud.chatapp.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.garud.chatapp.constant.ErrorCodeEnum;
import com.garud.chatapp.exception.ChatAppException;
import com.garud.chatapp.model.User;
import com.garud.chatapp.model.UserRes;
import com.garud.chatapp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	// Method to register user
	public String registerUser(User user) {
		// Check if the user with the given phone number already exists
		
			if (userRepository.findByPhoneNumber(user.getPhoneNumber()) != null) {

				throw new ChatAppException(
						ErrorCodeEnum.USER_ALREADY_EXIST.getErrorCode(),
						ErrorCodeEnum.USER_ALREADY_EXIST.getErrorMessage(), 
						HttpStatus.BAD_REQUEST);
			}

			// Hash the password
			user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
			try {
			userRepository.save(user);

			return "user created successfully";
		} catch (Exception e) {
			
			log.info(e.getMessage());

			throw new ChatAppException(
					ErrorCodeEnum.GENERIC_ERROR.getErrorCode(),
					ErrorCodeEnum.GENERIC_ERROR.getErrorMessage(), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Method to authenticate user (login)
	public boolean authenticateUser(String phoneNumber, String password) {
		User user = userRepository.findByPhoneNumber(phoneNumber);
		if (user == null) {
			return false; // User not found
		}

		// Check if the password matches
		return passwordEncoder.matches(password, user.getPasswordHash());
	}

	public UserRes getUserById(int userId) {

		User user = userRepository.findById(userId).orElse(null);

		UserRes userRes = modelMapper.map(user, UserRes.class);

		return userRes;

	}

	public UserRes getUserByPhoneNumber(String phoneNumber) {
		User user = userRepository.findByPhoneNumber(phoneNumber);

		UserRes userRes = modelMapper.map(user, UserRes.class);

		return userRes;
	}

	public List<UserRes> getAllUsers() {
		try {

			List<User> list = userRepository.findAll();

			List<UserRes> resList = new ArrayList<>();

			for (User user : list) {
				resList.add(modelMapper.map(user, UserRes.class));
			}
			return resList;
		} catch (Exception e) {

			log.info(e.getMessage());

			throw new ChatAppException(
					ErrorCodeEnum.GENERIC_ERROR.getErrorCode(),
					ErrorCodeEnum.GENERIC_ERROR.getErrorMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
