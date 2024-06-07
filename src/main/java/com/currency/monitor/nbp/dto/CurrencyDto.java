package com.currency.monitor.nbp.dto;

import com.currency.monitor.basic.BasicCurrency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@SuperBuilder
public class CurrencyDto extends BasicCurrency {
    private String table;
    private List<RatesDto> rates;

    public CurrencyDto(String code, String currency, String table, List<RatesDto> rates) {
        super(code, currency);
        this.table = table;
        this.rates = rates;
    }
}