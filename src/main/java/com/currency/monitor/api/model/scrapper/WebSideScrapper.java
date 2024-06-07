package com.currency.monitor.api.model.scrapper;

import com.currency.monitor.exception.ElementsCreateException;
import io.vavr.control.Try;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public abstract class WebSideScrapper {
    private final Document document;
    private String elementByClass;
    private String elementByTag;


    protected WebSideScrapper(Document document, String byClass, String byTag) {
        this.document = document;
        this.elementByClass = byClass;
        this.elementByTag = byTag;
    }

    protected WebSideScrapper(Document document) {
        this.document = document;
    }

    public Elements getElementsByClass() {
        return Try.of(() -> document.getElementsByClass(elementByClass))
                .getOrElseThrow(ElementsCreateException::becauseElementsHasErrorByClass);
    }

    public Elements getElementsByTag() {
        return Try.of(() -> document.getElementsByTag(elementByTag))
                .getOrElseThrow(ElementsCreateException::becauseElementsHasErrorByTag);
    }
}