package com.github.djroush.demo.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ConcurrentSchedulingException extends Exception {
	private static final long serialVersionUID = -1905305005027472995L;
	public ConcurrentSchedulingException() {
        super();
    }
    public ConcurrentSchedulingException(String message, Throwable cause) {
        super(message, cause);
    }
    public ConcurrentSchedulingException(String message) {
        super(message);
    }
    public ConcurrentSchedulingException(Throwable cause) {
        super(cause);
    }
}
