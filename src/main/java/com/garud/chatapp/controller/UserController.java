package com.garud.chatapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garud.chatapp.model.User;
import com.garud.chatapp.model.UserRes;
import com.garud.chatapp.pojo.request.LoginPojoReq;
import com.garud.chatapp.pojo.request.SignUpPojoReq;
import com.garud.chatapp.pojo.response.UserPojoRes;
import com.garud.chatapp.service.UserService;




@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")

public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/{id}")
	public UserPojoRes getUserById(@PathVariable int id) {

		UserRes res = userService.getUserById(id);

		UserPojoRes pojoRes = modelMapper.map(res, UserPojoRes.class);

		return pojoRes;

	}

	@GetMapping("/phone/{phoneNumber}")
	public UserPojoRes getUserByPhoneNumber(@PathVariable String phoneNumber) {

		UserRes res = userService.getUserByPhoneNumber(phoneNumber);

		UserPojoRes pojoRes = modelMapper.map(res, UserPojoRes.class);

		return pojoRes;
	}

	@GetMapping
	public List<UserPojoRes> getAllUsers() {

		List<UserRes> list = userService.getAllUsers();

		List<UserPojoRes> resList = new ArrayList<>();

		for (UserRes userRes : list) {
			resList.add(modelMapper.map(userRes, UserPojoRes.class));

		}

		return resList;
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody SignUpPojoReq signUpRequest) {

		try {
			User user = modelMapper.map(signUpRequest, User.class);

			String registerUser = userService.registerUser(user);

			return new ResponseEntity<String>(registerUser, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<String>("invalid input", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginPojoReq loginRequest) {

		boolean authenticated = userService.authenticateUser(loginRequest.getPhoneNumber(), loginRequest.getPassword());
		if (authenticated) {
			return new ResponseEntity<Map<String, String>>(Map.of("message", "login success"), HttpStatus.OK);
		} else {

			return new ResponseEntity<Map<String, String>>(Map.of("message", "Invalid phone number or password"),
					HttpStatus.BAD_REQUEST);
		}
	}
}
