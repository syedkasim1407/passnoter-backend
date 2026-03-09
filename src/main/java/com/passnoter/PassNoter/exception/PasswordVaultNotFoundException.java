package com.passnoter.PassNoter.exception;

public class PasswordVaultNotFoundException extends RuntimeException {
    public PasswordVaultNotFoundException(Long id) {
        super("Password entry with id " + id + " not found.");
    }
}
