package com.currency.monitor.service;

import com.currency.monitor.api.model.dto.CurrencyExchangeDto;
import com.currency.monitor.api.model.websideline.WebSideLine;
import com.currency.monitor.nbp.dto.CurrencyDto;

import java.util.List;
import java.util.Set;

public interface CurrencyService {
    List<WebSideLine> getAllCodes();
    List<WebSideLine> getAllRatesForCantor();
    Set<CurrencyExchangeDto> currencyExchangeDtos();

    CurrencyDto getCurrency(String code, String table, String date);
    CurrencyDto getExchangeRatesOfLastTenDays(String code);
}
