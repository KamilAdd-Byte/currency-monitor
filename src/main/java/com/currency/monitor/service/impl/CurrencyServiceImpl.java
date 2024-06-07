package com.currency.monitor.service.impl;

import com.currency.monitor.api.model.dto.CurrencyExchangeDto;
import com.currency.monitor.api.model.websideline.WebSideLine;
import com.currency.monitor.nbp.dto.CurrencyDto;
import com.currency.monitor.nbp.mapper.CurrencyWebMapper;
import com.currency.monitor.nbp.mapper.impl.CurrencyWebMapperImpl;
import com.currency.monitor.scrapp.cantor.service.impl.RatesCantorServiceImpl;
import com.currency.monitor.scrapp.currency.service.CurrencyCodesService;
import com.currency.monitor.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyWebMapper currencyWebMapper;
    @Autowired
    private CurrencyCodesService codesService;

    @Autowired
    private RatesCantorServiceImpl ratesCantorService;

    @Autowired
    public CurrencyServiceImpl(CurrencyWebMapperImpl currencyWebClient) {
        this.currencyWebMapper = currencyWebClient;
    }

    /**
     * @return 
     */
    @Override
    public List<WebSideLine> getAllCodes() {
        return codesService.getAllCodes();
    }

    @Override
    public List<WebSideLine> getAllRatesForCantor() {
        return ratesCantorService.getAllRatesForCantor();
    }

    @Override
    public Set<CurrencyExchangeDto> currencyExchangeDtos() {
        return codesService.allCurrencies();
    }

    /**
     * @param code currency is a three-letter String and standard ISO 4217
     * @param table currency is three-options 'A' 'B' or 'C'
     * @param date of publication of the value for the currency
     * @return new CurrencyDto if exist
     */
    @Override
    public CurrencyDto getCurrency(String code, String table, String date) {
        return this.currencyWebMapper.getOneCurrencyBy(code,table,date);
    }

    /**
     * @param code it's currency code standard ISO 4217
     * @return currency and ten rates get to nbp api by rest template
     */
    public CurrencyDto getExchangeRatesOfLastTenDays(String code) {
        return this.currencyWebMapper.getExchangeRatesOfLastTenDays(code);
    }
}
