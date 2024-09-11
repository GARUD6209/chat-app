package com.garud.chatapp.repository;

import com.garud.chatapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
	
    List<Message> findByConversation_ConversationId(int conversationId);  // Use underscore to navigate through the relation
    
}
