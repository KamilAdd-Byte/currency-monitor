package com.currency.monitor.exception;

public class DocumentCreateException extends RuntimeException {

    public DocumentCreateException(String message) {
        super(message);
    }

    public static DocumentCreateException becauseDocumentNotCreated() {
        return new DocumentCreateException("Document has not been created");
    }
}
