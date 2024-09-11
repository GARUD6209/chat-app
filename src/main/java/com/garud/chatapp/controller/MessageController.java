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

import com.garud.chatapp.model.Message;
import com.garud.chatapp.model.MessageRes;
import com.garud.chatapp.pojo.request.MessagePojoReq;
import com.garud.chatapp.pojo.response.MessagePojoRes;
import com.garud.chatapp.service.MessageService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/messages")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/conversation/{conversationId}")
	public List<MessagePojoReq> getMessagesByConversationId(@PathVariable int conversationId) {
		
		List<MessageRes> list = messageService.getMessagesByConversationId(conversationId);
		
		List<MessagePojoReq> resList =new ArrayList<>();
		
		for (MessageRes messageRes : list) {
			resList.add(modelMapper.map(messageRes, MessagePojoReq.class));
		}
		
		return resList;
	}

	@PostMapping
    public MessagePojoRes sendMessage(@RequestBody MessagePojoReq messageReq) {
    	
    	Message message = modelMapper.map(messageReq,Message.class);
    	
    	MessageRes messageRes = messageService.saveMessage(message);
    	
    	MessagePojoRes messagePojoRes = modelMapper.map(messageRes,MessagePojoRes.class);
    	
        return messagePojoRes;
    }
}
