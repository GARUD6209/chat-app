package com.garud.chatapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.garud.chatapp.constant.ErrorCodeEnum;
import com.garud.chatapp.pojo.response.ErrorResponse;

@ControllerAdvice
public class ChatAppExceptionHandler {
	
	
	@ExceptionHandler(ChatAppException.class)
	public ResponseEntity<ErrorResponse> handlePaymentProcessingException(
			        ChatAppException ex){
		
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(ex.getErrorCode());
		errorResponse.setErrorMessage(ex.getErrorMessage());
		
				
		
		ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, ex.getHttpStatus());
		
		
		return responseEntity;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(
			       Exception ex){
		
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(ErrorCodeEnum.GENERIC_ERROR.getErrorCode());
		errorResponse.setErrorMessage(ErrorCodeEnum.GENERIC_ERROR.getErrorMessage());
				
		
		ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
		
		return responseEntity;
	}

}
