package com.currency.monitor.api.response;

import com.currency.monitor.api.model.dto.CurrencyExchangeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyCodeResponse {
    private String requestId;
    private Date requestCreated;
    private Set<CurrencyExchangeDto> currencyDto;
}
