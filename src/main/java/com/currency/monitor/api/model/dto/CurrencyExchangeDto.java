package com.currency.monitor.api.model.dto;

import com.currency.monitor.basic.BasicCurrency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@ToString
public class CurrencyExchangeDto extends BasicCurrency {
    private String country;
    private String number;

    public CurrencyExchangeDto(String code, String currency, String country, String number) {
        super(code, currency);
        this.country = country;
        this.number = number;
    }
}
