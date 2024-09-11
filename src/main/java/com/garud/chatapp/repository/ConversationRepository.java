package com.garud.chatapp.repository;

import com.garud.chatapp.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
    List<Conversation> findByParticipant1IdOrParticipant2Id(int participant1Id, int participant2Id);
}
