package com.currency.monitor.scrapp.utils;


import com.currency.monitor.exception.DocumentCreateException;
import com.currency.monitor.message.LogMessage;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Create new jsoup document
 */
@Slf4j
@Component
public class DocumentCreator {

    /**
     * @param baseUri uri web-side address to scrap
     * @return new document ready to scrapping
     */
    public Document create (String baseUri) {
        return Try.of(()-> createNewDocument(baseUri))
                .onSuccess(doc -> log.info(LogMessage.documentCreatedMessage()))
                .onFailure(fail -> log.error(LogMessage.documentNotCreatedMessage()))
                .getOrElseThrow(DocumentCreateException::becauseDocumentNotCreated);
    }

    private Document createNewDocument(String baseUri) throws IOException {
        log.debug("Starting connect jsoup to uri [{}]", baseUri);
        return Jsoup.connect(baseUri).get();
    }
}
