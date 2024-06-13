package com.currency.monitor.export.lineitem;

import com.currency.monitor.nbp.gold.dto.GoldDto;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CSVExportProcessorTest {

    @Test
    public void testPrintValueGoldToCsv() throws IOException {
        // Given
        GoldDto[] goldData = {
                GoldDto.builder().data("2024-04-24").cena("304.01").build(),
                GoldDto.builder().data("2024-04-25").cena("301.51").build(),
        };
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // When
        CSVExportProcessor.printValueGoldToCsv(goldData, outputStream);

        // Then
        String csvOutput = outputStream.toString(StandardCharsets.UTF_8);
        String expectedCsv = "data,cena\n2024-04-24,304.01\n2024-04-25,301.51\n";

        assertThat(csvOutput).isEqualTo(expectedCsv);
    }

    @Test
    public void testPrintValueGoldToCsvNull() {
        GoldDto[] goldData = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        assertThatThrownBy(() -> {
            CSVExportProcessor.printValueGoldToCsv(goldData, outputStream);
        }).isInstanceOf(NullPointerException.class);
    }
}