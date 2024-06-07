package com.currency.monitor.scrapp.currency.service;

import com.currency.monitor.api.model.dto.CurrencyExchangeDto;
import com.currency.monitor.api.model.websideline.WebSideLine;

import java.util.List;
import java.util.Set;

public interface CurrencyCodesService {
    List<WebSideLine> getAllCodes();
    Set<CurrencyExchangeDto> allCurrencies();
}
