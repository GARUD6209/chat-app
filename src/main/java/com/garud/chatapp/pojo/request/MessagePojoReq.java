package com.garud.chatapp.pojo.request;

import java.util.Date;

import com.garud.chatapp.model.Conversation;
import com.garud.chatapp.model.User;

import lombok.Data;

@Data
public class MessagePojoReq {
	 private int messageId;

	  
	    private User sender; 

	   
	    private Conversation conversation; 

	  
	    private String content;

	  
	    private Date createdAt;
}
