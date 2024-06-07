package com.currency.monitor.scrapp.currency;

import com.currency.monitor.api.model.dto.CurrencyExchangeDto;
import com.currency.monitor.api.model.websideline.WebSideLine;
import com.currency.monitor.message.LogMessage;
import io.vavr.control.Try;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kamil.sulejewski
 * @apiNote Responsibility of the class is to prepare a list of currency exchanges dtos based on the webSideLines list received from WebSideScraper
 * Class uses WebSideLineValidator to catch waste lines
 * @see WebSideLineValidator,CurrencyCodesWebSideScrapper
 */
@Slf4j
public class CurrencyExchangeObtainer {

    private final List<WebSideLine> webSideLines;

    @Getter
    private Set<CurrencyExchangeDto> exchangeDtos = new HashSet<>();

    private CurrencyExchangeObtainer(List<WebSideLine> webSideLines) {
        this.webSideLines = webSideLines;
        prepareCurrenciesExchangeDtos();
    }

    public static CurrencyExchangeObtainer of(List<WebSideLine> webSideLines) {
        return new CurrencyExchangeObtainer(webSideLines);
    }

    private void prepareCurrenciesExchangeDtos() {
        exchangeDtos = webSideLines.stream()
                .map(webSideLine -> {
                    String[] webSideLineArray = splitWebSideLine(webSideLine.getLine());
                    return getCurrencyExchange(webSideLineArray);
                }).collect(Collectors.toSet());
    }

    /**
     * @param webSideLineArray post-validation array
     * @return {@link CurrencyExchangeDto}
     */
    private CurrencyExchangeDto getCurrencyExchange (String[] webSideLineArray) {
        return Try.of(() -> obtainCurrency(webSideLineArray))
                .onFailure(throwable -> log.error(LogMessage.currencyExchangeFailureObtainMessage(throwable.getMessage())))
                .onSuccess(cxd -> log.info(LogMessage.currencyExchangeSuccessObtainMessage(cxd.getCurrency())))
                .getOrElse(new CurrencyExchangeDto());
    }

    private CurrencyExchangeDto obtainCurrency (String[] webSideLineArray) {
        return CurrencyExchangeDto.builder()
                .country(webSideLineArray[0])
                .currency(webSideLineArray[1])
                .code(webSideLineArray[2])
                .number(webSideLineArray[3])
                .build();
    }

    /**
     * Major method which split WebSideLine for
     * {@link WebSideLineValidator#defaultWebSideLineValue()}
     */
    private String[] splitWebSideLine(String webSideLine) {
        String webSideLineWithoutWhitespace = StringUtils.deleteWhitespace(webSideLine);
        String webSideLineWithoutBrackets = getWebSideLineWithoutBrackets(webSideLineWithoutWhitespace);
        if (WebSideLineValidator.validate(webSideLineWithoutBrackets).isHasWaste()) {
            webSideLineWithoutBrackets = WebSideLineValidator.defaultWebSideLineValue();
        }
        return getSplitByCurrencyPattern(webSideLineWithoutBrackets);
    }

    /**
     * Arrays should be length at 4 (CurrencyExchangeDto fields representation-pattern). If array has length more at 4, method creates default array
     * {@link WebSideLineValidator#defaultWebSideLineArrays()}
     */
    private static String[] getSplitByCurrencyPattern(String webSideLineWithoutWhitespace) {
        String[] splitByCharacterTypeCamelCase = StringUtils.splitByCharacterTypeCamelCase(webSideLineWithoutWhitespace);
        if (splitByCharacterTypeCamelCase.length >= 5) {
            splitByCharacterTypeCamelCase = WebSideLineValidator.defaultWebSideLineArrays();
        }
        return splitByCharacterTypeCamelCase;
    }

    /**
     * @param webSideLineWithoutWhitespace once line
     * @return String without brackets and without string between
     */
    private String getWebSideLineWithoutBrackets(String webSideLineWithoutWhitespace) {
        String webSideLineWithoutBrackets = "";
        if (webSideLineWithoutWhitespace.contains("(")) {
            log.warn("Line has special characters [{}]", webSideLineWithoutWhitespace);
            String substringed = StringUtils.substringBetween(webSideLineWithoutWhitespace, "(", ")");
            webSideLineWithoutBrackets = StringUtils.replaceEach(webSideLineWithoutWhitespace, new String[]{"(", substringed, "Inversion", ")"}, new String[]{"","","",""});
            log.warn("Removed characters, webSideLine without brackets: [{}]", webSideLineWithoutBrackets);
            return webSideLineWithoutBrackets;
        }
        return webSideLineWithoutWhitespace;
    }
}

@Slf4j
class WebSideLineValidator {
    private final String value;

    @Getter
    boolean hasWaste;

    private WebSideLineValidator(String value) {
        this.value = value;
        this.hasWaste = validateLine();
    }

    protected static String defaultWebSideLineValue() {
        return "POLSKA Zloty PLN 3220";
    }

    protected static String[] defaultWebSideLineArrays() {
        return new String[]{"POLSKA","Zloty","PLN","3220"};
    }

    public static WebSideLineValidator validate(String value) {
        return new WebSideLineValidator(value);
    }

    private boolean validateLine() {
        hasWaste = listOfWastes().stream().anyMatch(value::contains);
        if (hasWaste) {
            log.info("WebSideLine has waste: [{}]", value);
        }
        return hasWaste;
    }

    private List<String> listOfWastes() {
        List<String> wastes = new ArrayList<>();
        wastes.add("MIĘDZYNARODOWYFUNDUSZWALUTOWY");
        wastes.add("ANTARKTYDA Brak uniwersalnej waluty");
        wastes.add("WIELONARODOWEGOPAŃSTWA");
        return wastes;
    }
}


