package com.garud.chatapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garud.chatapp.model.Conversation;
import com.garud.chatapp.model.ConversationRes;
import com.garud.chatapp.model.MessageRes;
import com.garud.chatapp.pojo.request.ConversationPojoReq;
import com.garud.chatapp.pojo.request.MessagePojoReq;
import com.garud.chatapp.pojo.response.ConversationPojoRes;
import com.garud.chatapp.service.ConversationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/conversations")
public class ConversationController {

	@Autowired
	private ConversationService conversationService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/user/{userId}")
	public List<ConversationPojoRes> getConversationsForUser(@PathVariable int userId) {
		
		List<ConversationRes> list = conversationService.getConversationsForUser(userId);

		List<ConversationPojoRes> resList = new ArrayList<>();

		for (ConversationRes res : list) {
			resList.add(modelMapper.map(res, ConversationPojoRes.class));
		}

		return resList;
		
	}

	@PostMapping
	public ConversationPojoRes createConversation(@RequestBody ConversationPojoReq req) {

		Conversation conversation = modelMapper.map(req, Conversation.class);

		ConversationRes res = conversationService.createConversation(conversation);

		ConversationPojoRes pojoRes = modelMapper.map(res, ConversationPojoRes.class);

		return pojoRes;
	}
}
