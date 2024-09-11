package com.garud.chatapp.pojo.request;

import java.util.Date;

import lombok.Data;

@Data
public class MessageResPojo {
	
	 private int messageId;
	    private String content;
	    private Date createdAt;

}
