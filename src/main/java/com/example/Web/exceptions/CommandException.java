package com.example.Web.exceptions;

import com.example.Web.enums.ErrorCode;

public class CommandException extends RuntimeException{
	public CommandException(ErrorCode errorCode) {
		super(errorCode.getMessage());
	}
}
