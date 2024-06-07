package com.currency.monitor.nbp.gold;

import com.currency.monitor.export.lineitem.CSVExportProcessor;
import com.currency.monitor.nbp.gold.dto.GoldDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class NbpValueGoldService {
    private final RestTemplate restTemplate;
    private final String goldApiUrl;

    public NbpValueGoldService(RestTemplate restTemplate, @Value("${currency-url.basic-gold}") String goldApiUrl) {
        this.restTemplate = restTemplate;
        this.goldApiUrl = goldApiUrl;
    }

    public List<GoldDto> getTenGoldResultValue() throws IOException {
        GoldDto[] goldResult = restTemplate.getForObject(goldApiUrl, GoldDto[].class);
        return goldResult != null ? Arrays.asList(goldResult) : Collections.emptyList();
    }

    public GoldDto[] getTeenGoldResult() {
        return restTemplate.getForObject(goldApiUrl, GoldDto[].class);
    }
}
