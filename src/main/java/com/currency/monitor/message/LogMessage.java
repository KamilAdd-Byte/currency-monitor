package com.currency.monitor.message;

public class LogMessage {

    private static final String JSOUP_CREATED_DOCUMENT = "Document is created";
    private static final String JSOUP_NOT_CREATED_DOCUMENT = "Document is not created";
    private static final String EXCHANGE_OBTAINED_SUCCESSFULLY = "Currency exchange has obtain successful";
    private static final String EXCHANGE_OBTAINED_FAILURE = "Currencies are not obtained by error";


    private LogMessage() {
        throw new IllegalStateException("Utility class");
    }

    // Jsoup Document
    public static String documentCreatedMessage() {
        return JSOUP_CREATED_DOCUMENT;
    }

    public static String documentNotCreatedMessage() {
        return JSOUP_NOT_CREATED_DOCUMENT;
    }

    // Currency obtain
    public static String currencyExchangeSuccessObtainMessage(String value) {
        return EXCHANGE_OBTAINED_SUCCESSFULLY;
    }

    public static String currencyExchangeFailureObtainMessage(String value) {
        return EXCHANGE_OBTAINED_FAILURE;
    }
}
