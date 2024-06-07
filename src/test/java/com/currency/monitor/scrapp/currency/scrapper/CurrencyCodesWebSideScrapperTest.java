package com.currency.monitor.scrapp.currency.scrapper;

import com.currency.monitor.exception.ElementsCreateException;
import com.currency.monitor.scrapp.currency.service.impl.CurrencyCodesServiceImpl;
import com.currency.monitor.scrapp.utils.DocumentCreator;
import com.currency.monitor.service.CurrencyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {CurrencyService.class})
class CurrencyCodesWebSideScrapperTest {

    @MockBean
    private CurrencyService service;

    @MockBean
    private DocumentCreator documentCreator;

    @Test
    @DisplayName("get connection wikipedia and should status 200")
    void getConnectionWikipedia_andShouldStatus_200ok() {
        String url = CurrencyCodesServiceImpl.getUrlBasicCodeCurrency();

        when()
                .get(url)
                .then()
                .statusCode(200);
    }

    @Test
    void test_Element_is_not_created_because_has_errors_by_class() {
        ElementsCreateException exception = assertThrows(ElementsCreateException.class, () ->
                CurrencyCodesWebSideScrapper.of(null));
        assertEquals("Element is not created because has errors by class", exception.getMessage());
    }
}