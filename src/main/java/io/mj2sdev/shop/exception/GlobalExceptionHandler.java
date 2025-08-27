package io.mj2sdev.shop.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleAccountNotFound(AccountNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
			bodyMapTemplate(ex, "Account Not Found")
		);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
			bodyMapTemplate(ex, "Internal Server Error")
		);
	}

	private Map<String, Object> bodyMapTemplate(Throwable e, String message) {
		return Map.of(
			"timestamp", LocalDateTime.now(),
			"error", message,
			"message", e.getMessage()
		);
	}
}
