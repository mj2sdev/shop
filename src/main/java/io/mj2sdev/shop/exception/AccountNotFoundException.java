package io.mj2sdev.shop.exception;

public class AccountNotFoundException extends RuntimeException {
	public AccountNotFoundException(String username) {
		super("계정을 찾을 수 없습니다. [%s]".formatted(username));
	}
}
