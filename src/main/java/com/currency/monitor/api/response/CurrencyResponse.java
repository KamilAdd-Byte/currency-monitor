package com.currency.monitor.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyResponse<T> {
    private String requestId;
    private Date requestCreated;
    private String requestCurrencyData;
    private T currencyDto;
}
