package com.currency.monitor.export.lineitem;

import com.currency.monitor.nbp.gold.dto.GoldDto;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVExportProcessor {

    public static void printValueGoldToCsv(GoldDto[] goldResult, OutputStream outputStream) throws IOException {
        ObjectWriter writer = getObjectWriter();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
            writer.writeValues(bufferedWriter).writeAll(goldResult);
        }
    }

    private static ObjectWriter getObjectWriter() {
        CsvMapper mapperCsv = new CsvMapper();
        mapperCsv.enable(CsvParser.Feature.EMPTY_STRING_AS_NULL);

        CsvSchema columns = CsvSchema.builder().setUseHeader(true)
                .addColumn("data")
                .addColumn("cena")
                .build();

        return mapperCsv.writerWithSchemaFor(GoldDto.class).with(columns);
    }
}
