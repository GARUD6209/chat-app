package com.garud.chatapp.model;

import java.util.Date;

import lombok.Data;

@Data
public class MessageRes {
	
	 private int messageId;
	    private String content;
	    private Date createdAt;

}
