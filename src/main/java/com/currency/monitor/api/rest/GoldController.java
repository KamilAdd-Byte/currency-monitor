package com.currency.monitor.api.rest;

import com.currency.monitor.export.lineitem.CSVExportProcessor;
import com.currency.monitor.nbp.gold.NbpValueGoldService;
import com.currency.monitor.nbp.gold.dto.GoldDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1")
@Tag(name = "Gold monitor API", description = "Services for gold. Get some information about gold from nbp.api")
public class GoldController {

    private final NbpValueGoldService goldService;

    @GetMapping(path = "/gold")
    @Operation(description = "Get exchange gold")
    public ResponseEntity<List<GoldDto>> getExchangeRatesOfLastTenDays () throws IOException {
        List<GoldDto> tenCurrencyBy = goldService.getTenGoldResultValue();
        return ResponseEntity.ok().body(tenCurrencyBy);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadCsv() {
        try {
            ByteArrayOutputStream csvFile = goldService.getCsvFile();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=gold.csv");
            headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

            return new ResponseEntity<>(csvFile.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
