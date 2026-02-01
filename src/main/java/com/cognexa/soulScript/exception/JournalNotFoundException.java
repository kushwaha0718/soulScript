package com.cognexa.soulScript.exception;

public class JournalNotFoundException extends RuntimeException {
    public JournalNotFoundException(String message) {
        super(message);
    }
}
