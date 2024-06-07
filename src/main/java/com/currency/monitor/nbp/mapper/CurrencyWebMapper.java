package com.currency.monitor.nbp.mapper;

import com.currency.monitor.nbp.dto.CurrencyDto;

public interface CurrencyWebMapper {
    CurrencyDto getExchangeRatesOfLastTenDays(String code);
    CurrencyDto getOneCurrencyBy(String code, String table, String date);
}
