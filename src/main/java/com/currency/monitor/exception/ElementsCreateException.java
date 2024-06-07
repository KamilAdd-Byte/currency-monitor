package com.currency.monitor.exception;

public class ElementsCreateException extends DocumentCreateException{
    public ElementsCreateException(String message) {
        super(message);
    }

    public static ElementsCreateException becauseElementsHasErrorByClass() {
        return new ElementsCreateException("Element is not created because has errors by class");
    }
    public static ElementsCreateException becauseElementsHasErrorByTag() {
        return new ElementsCreateException("Element is not created because has errors by tag");
    }
}
