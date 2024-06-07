package com.currency.monitor.nbp.gold.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonPropertyOrder({"data","cena"})
public class GoldDto {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String data;
    private String cena;
}
