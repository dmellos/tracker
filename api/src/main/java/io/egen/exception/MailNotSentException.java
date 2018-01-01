package io.egen.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
@SuppressWarnings("serial")
public class MailNotSentException extends RuntimeException {
	public MailNotSentException(String message) {
		super(message);
	}
}
