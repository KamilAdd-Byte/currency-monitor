package com.currency.monitor.exception;

public class CurrencyExchangeObtainException extends RuntimeException {

    private String[] line;
    public CurrencyExchangeObtainException(String message, String[] line) {
        super(message);
        this.line = line;
    }

    public static CurrencyExchangeObtainException becauseCurrencyFailedObtain (String[] line) {
        return new CurrencyExchangeObtainException("Currencies are not obtained by error",line);
    }
}
