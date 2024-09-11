package com.garud.chatapp.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garud.chatapp.model.Conversation;
import com.garud.chatapp.model.ConversationRes;
import com.garud.chatapp.repository.ConversationRepository;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;
    
    @Autowired
	private ModelMapper modelMapper;

    public List<ConversationRes> getConversationsForUser(int userId) {
    	
    	List<Conversation> list = conversationRepository.findByParticipant1IdOrParticipant2Id(userId, userId);
    	
    	List<ConversationRes> resList = new ArrayList<>();

		for (Conversation conversation : list) {
			resList.add(modelMapper.map(conversation, ConversationRes.class));
		}
       
    	return resList;
        
    }

    public ConversationRes createConversation(Conversation conversation) {
    	
    	Conversation res = conversationRepository.save(conversation);
    	
    	ConversationRes conRes = modelMapper.map(res, ConversationRes.class);
    	
        return conRes;
    }
}
