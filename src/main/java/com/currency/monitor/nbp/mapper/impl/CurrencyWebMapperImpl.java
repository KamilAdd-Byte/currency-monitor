package com.currency.monitor.nbp.mapper.impl;

import com.currency.monitor.nbp.dto.CurrencyDto;
import com.currency.monitor.nbp.mapper.CurrencyWebMapper;
import com.currency.monitor.nbp.model.CurrencyNbpExternalModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Responsibility getting currency from npb api and mapping/building it to CurrencyDto
 */
@Slf4j
@Component
public class CurrencyWebMapperImpl implements CurrencyWebMapper {
    //url model: https://api.nbp.pl/api/exchangerates/rates/A/USD/last/10
    private static final String BASIC_NBP_URL = "https://api.nbp.pl/api/exchangerates/rates/";
    private static final String TABLE_A = "/A/";
    private static final String LAST_TOP_10 = "/last/10";

    private static CurrencyWebMapperImpl instance;
    private final RestTemplate restTemplate = new RestTemplate();

    private CurrencyWebMapperImpl() {
    }

    public static CurrencyWebMapperImpl getInstance() {
        if (instance == null) {
            instance = new CurrencyWebMapperImpl();
        }
        return instance;
    }

    /**
     * @param code are the three characters that make up the currency code standard ISO 4217
     * @return currency and ten rates get to nbp api by rest template from table A
     */
    @Override
    public CurrencyDto getExchangeRatesOfLastTenDays(String code) {
        return getLastTenRates(code, TABLE_A);
    }

    private CurrencyDto getLastTenRates(String code, String table) {
        CurrencyNbpExternalModel currencyNbpExternalModel = restTemplate.getForObject(BASIC_NBP_URL + table +
                code.toUpperCase() + LAST_TOP_10, CurrencyNbpExternalModel.class, code);
        assert currencyNbpExternalModel != null;
        log.info("Currency get successfully {}", currencyNbpExternalModel.getCode());

        return CurrencyDto.builder()
                .table(currencyNbpExternalModel.getTable())
                .currency(currencyNbpExternalModel.getCurrency())
                .code(currencyNbpExternalModel.getCode())
                .rates(currencyNbpExternalModel.getRates())
                .build();
    }

    @Override
    public CurrencyDto getOneCurrencyBy(String code, String table, String date) {
        CurrencyNbpExternalModel currencyNbpExternalModel = restTemplate.getForObject(BASIC_NBP_URL + table + "/" +
                code.toUpperCase() + "/" + date, CurrencyNbpExternalModel.class, code);
        assert currencyNbpExternalModel != null;
        log.info("Currency get successfully {}", currencyNbpExternalModel.getCode());

        return CurrencyDto.builder()
                .table(currencyNbpExternalModel.getTable())
                .currency(currencyNbpExternalModel.getCurrency())
                .code(currencyNbpExternalModel.getCode())
                .rates(currencyNbpExternalModel.getRates())
                .build();
    }
}
