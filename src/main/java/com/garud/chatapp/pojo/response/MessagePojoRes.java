package com.garud.chatapp.pojo.response;

import java.util.Date;

import lombok.Data;

@Data
public class MessagePojoRes {
	 private int messageId;
	    private String content;
	    private Date createdAt;
}
