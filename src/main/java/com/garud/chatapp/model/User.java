package com.garud.chatapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "users")
public class User {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int userId;

	    @Column(name = "phone_number", nullable = false)
	    private String phoneNumber;

	    @Column(name = "username")
	    private String username;

	    @Column(name = "profile_picture_url")
	    private String profilePictureUrl;

	    @Column(name = "last_seen")
	    private Timestamp lastSeen;

	    @Column(name = "is_online")
	    private boolean isOnline;

	    @Column(name = "password_hash")
	    private String passwordHash;

	    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	    @JsonIgnore
	    private List<Message> messages; // A user can send many messages

  
}
