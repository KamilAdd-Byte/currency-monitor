package com.currency.monitor.api.rest;

import com.currency.monitor.api.model.dto.CurrencyExchangeDto;
import com.currency.monitor.api.model.websideline.WebSideLine;
import com.currency.monitor.api.response.CurrencyCodeResponse;
import com.currency.monitor.api.response.CurrencyResponse;
import com.currency.monitor.nbp.dto.CurrencyDto;
import com.currency.monitor.service.impl.CurrencyServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
@Tag(name = "Currency monitor API", description = "Services for currencies. Get currency from nbp.api or data from scrap")
public class CurrencyController {

    private final CurrencyServiceImpl service;


    @GetMapping(path = "/codes")
    @Operation(summary = "Get all codes")
    public ResponseEntity<List<WebSideLine>> getAllCodes () {
        List<WebSideLine> allCodes = service.getAllCodes();
        return ResponseEntity.ok().body(allCodes);
    }

    @GetMapping(path = "/currencies")
    @Operation(summary = "Get all currencies")
    public ResponseEntity<CurrencyCodeResponse> getAllCurrencies () {
        String id = UUID.randomUUID().toString();
        Date requestDate = new Date();
        Set<CurrencyExchangeDto> currencyExchangeDtos = service.currencyExchangeDtos();
        return new ResponseEntity<>(new CurrencyCodeResponse(id, requestDate, currencyExchangeDtos), HttpStatus.OK);
    }

    @GetMapping(path = "/{code}/{table}/{date}")
    @Operation(description = "Get rates for currency by code, table and date")
    public ResponseEntity<CurrencyResponse> getCurrency(@PathVariable("code") String code, @PathVariable("table") String table, @PathVariable("date") String date) {
        String id = UUID.randomUUID().toString();
        Date requestDate = new Date();
        CurrencyDto currency = service.getCurrency(code, table, date);
       return new ResponseEntity<>(new CurrencyResponse(id, requestDate, date, currency), HttpStatus.OK);
    }

    @GetMapping(path = "/{code}")
    @Operation(description = "Get exchange rates of ten days for currency by CODE")
    public ResponseEntity<CurrencyDto> getExchangeRatesOfLastTenDays (@PathVariable("code") String code) {
        CurrencyDto currency = service.getExchangeRatesOfLastTenDays(code);
        return ResponseEntity.ok().body(currency);
    }

    @GetMapping(path = "/cantor")
    @Operation(description = "Get rates currency exchange from cantor")
    public ResponseEntity<List<WebSideLine>> getRatesExampleCantor () {
        List<WebSideLine> allRatesForCantor = service.getAllRatesForCantor();
        return ResponseEntity.ok().body(allRatesForCantor);
    }
}
