package com.currency.monitor.scrapp.cantor.scrapper;

import com.currency.monitor.api.model.scrapper.WebSideScrapper;
import com.currency.monitor.api.model.websideline.WebSideLine;
import lombok.Getter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;

public class RatesCantorScrapper extends WebSideScrapper {

    @Getter
    private List<WebSideLine> webSideLines;

    private static final String SELECT_QUERY = "tr";
    private static final String ELEMENT_BY_CLASS = "sticky-enabled";
    private static final String ELEMENT_BY_TAG = "p";

    private RatesCantorScrapper(Document document) {
        super(document, ELEMENT_BY_CLASS, ELEMENT_BY_TAG);
        scrapAllCodesList();
    }

    public static RatesCantorScrapper of (Document document) {
        return new RatesCantorScrapper(document);
    }

    void scrapAllCodesList() {
        init();
        Elements elementsByClass = getElementsByClass();
        for (Element element : elementsByClass.select(SELECT_QUERY)) {
            webSideLines.add(new WebSideLine(element.text()));
        }
    }

    private void init() {
        if (webSideLines == null) {
            webSideLines = new ArrayList<>();
        }
    }
}
