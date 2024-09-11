package com.garud.chatapp.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garud.chatapp.model.Message;
import com.garud.chatapp.model.MessageRes;
import com.garud.chatapp.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
	  private ModelMapper modelMapper;
     
    public List<MessageRes> getMessagesByConversationId(int conversationId) {
    	
    	List<Message> list = messageRepository.findByConversation_ConversationId(conversationId);
    	
    	List<MessageRes> resList = new ArrayList<>();

		for (Message message : list) {
			resList.add(modelMapper.map(message, MessageRes.class));
		}
       
    	return resList;
    }

    public MessageRes saveMessage(Message messageReq) {
    	
    	 Message message = messageRepository.save(messageReq);
    	 
    	 MessageRes messageRes = modelMapper.map(message, MessageRes.class);
    	
        return messageRes;
    }
}
