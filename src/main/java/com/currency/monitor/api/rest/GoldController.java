package com.currency.monitor.api.rest;

import com.currency.monitor.nbp.dto.CurrencyDto;
import com.currency.monitor.nbp.gold.NbpValueGoldService;
import com.currency.monitor.nbp.gold.dto.GoldDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1")
@Tag(name = "Gold monitor API", description = "Services for gold. Get some information about gold from nbp.api")
public class GoldController {

    private final NbpValueGoldService goldService;

    @GetMapping(path = "/gold")
    @Operation(description = "Get exchange gold")
    public ResponseEntity<List<GoldDto>> getExchangeRatesOfLastTenDays () {
        List<GoldDto> tenCurrencyBy = goldService.getTenCurrencyBy();
        return ResponseEntity.ok().body(tenCurrencyBy);
    }
}
