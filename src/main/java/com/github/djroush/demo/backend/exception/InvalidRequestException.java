package com.github.djroush.demo.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends Exception {

	private static final long serialVersionUID = -2762192175227268643L;
    public InvalidRequestException() {
        super();
    }
    public InvalidRequestException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidRequestException(String message) {
        super(message);
    }
    public InvalidRequestException(Throwable cause) {
        super(cause);
    }
}
