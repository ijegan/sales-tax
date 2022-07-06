package com.itemis.salestax.common;

import java.time.LocalDateTime;

public class Response {
	private final boolean success;
	private final String message;
	// try again

	public Response(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public String getTimestamp() {
		return LocalDateTime.now().toString();
	}
}