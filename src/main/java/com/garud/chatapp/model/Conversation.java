package com.garud.chatapp.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "conversations")
public class Conversation {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int conversationId;

	    @Column(name = "participant1_id", nullable = false)
	    private int participant1Id;

	    @Column(name = "participant2_id", nullable = false)
	    private int participant2Id;

	    @Column(name = "created_at")
	    private Date createdAt;

	    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JsonIgnore
	    private List<Message> messages;  // This must match the field name in Message




    
}
