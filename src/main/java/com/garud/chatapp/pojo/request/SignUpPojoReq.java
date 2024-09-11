package com.garud.chatapp.pojo.request;

import lombok.Data;

@Data
public class SignUpPojoReq {

	private String phoneNumber;

	private String username;

	private String profilePictureUrl;

	private String passwordHash;

}
