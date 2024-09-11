package com.garud.chatapp.pojo.request;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ConversationPojoReq {

	private int conversationId;

	private int participant1Id;

	private int participant2Id;

	private Date createdAt;

	private List<MessagePojoReq> messages;

}
