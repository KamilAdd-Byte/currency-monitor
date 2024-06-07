package com.currency.monitor.scrapp.currency.service.impl;

import com.currency.monitor.api.model.dto.CurrencyExchangeDto;
import com.currency.monitor.api.model.websideline.WebSideLine;
import com.currency.monitor.scrapp.currency.CurrencyExchangeObtainer;
import com.currency.monitor.scrapp.currency.scrapper.CurrencyCodesWebSideScrapper;
import com.currency.monitor.scrapp.currency.service.CurrencyCodesService;
import com.currency.monitor.scrapp.utils.DocumentCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class CurrencyCodesServiceImpl implements CurrencyCodesService {

    private static final String URL_BASIC_CODE_CURRENCY = "https://www.iban.pl/currency-codes";

    @Autowired
    private DocumentCreator documentCreator;

    @Override
    public List<WebSideLine> getAllCodes() {
        return CurrencyCodesWebSideScrapper.of(documentCreator.create(URL_BASIC_CODE_CURRENCY)).getWebSideLines();
    }

    public static String getUrlBasicCodeCurrency() {
        return URL_BASIC_CODE_CURRENCY;
    }


    @Override
    public Set<CurrencyExchangeDto> allCurrencies() {
        List<WebSideLine> webSideLines = getAllCodes();
        return CurrencyExchangeObtainer.of(webSideLines).getExchangeDtos();
    }
}

