package com.currency.monitor.scrapp.currency.scrapper;


import com.currency.monitor.api.model.scrapper.WebSideScrapper;
import com.currency.monitor.api.model.websideline.WebSideLine;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kamil.sulejewski
 *
 * @apiNote The responsibility of the class is to obtain a WebSideLine in the form of a string based
 * on the provided jsoup document
 */
@Slf4j
public class CurrencyCodesWebSideScrapper extends WebSideScrapper {

    private static final String SELECT_QUERY = "tr";
    private static final String ELEMENT_BY_CLASS = "table table-bordered downloads tablesorter";
    private static final String ELEMENT_BY_TAG = "p";

    @Getter
    private List<WebSideLine> webSideLines;


    private CurrencyCodesWebSideScrapper(Document document) {
        super(document, ELEMENT_BY_CLASS, ELEMENT_BY_TAG);
        obtainWebSideLines();
    }

    public static CurrencyCodesWebSideScrapper of(Document document) {
        return new CurrencyCodesWebSideScrapper(document);
    }

    private void obtainWebSideLines() {
        if (webSideLines == null) {
            webSideLines = new ArrayList<>();
        }

        Elements elementsByClass = getElementsByClass();

        for (Element element : elementsByClass.select(SELECT_QUERY)) {
            webSideLines.add(new WebSideLine(element.text()));
           }
    }
}
