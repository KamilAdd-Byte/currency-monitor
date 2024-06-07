package com.currency.monitor.nbp.model;

import com.currency.monitor.nbp.dto.RatesDto;
import lombok.Getter;
import java.util.List;

@Getter
public class CurrencyNbpExternalModel {
    private String table;
    private String currency;
    private String code;
    private List<RatesDto> rates;
}
