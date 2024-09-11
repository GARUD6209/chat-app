package com.garud.chatapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "messages")
@Data
public class Message {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int messageId;

	    @ManyToOne
	    @JoinColumn(name = "sender_id", nullable = false)
	    private User sender; // References User entity

	    @ManyToOne
	    @JoinColumn(name = "conversation_id", nullable = false)
	    private Conversation conversation; // References Conversation entity

	    @Column(name = "content", nullable = false)
	    private String content;

	    @Column(name = "created_at")
	    private Date createdAt;
}
