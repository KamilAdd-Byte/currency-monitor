package com.currency.monitor.scrapp.cantor.service;

import com.currency.monitor.api.model.websideline.WebSideLine;
import java.util.List;

public interface RatesCantorService {
    List<WebSideLine> getAllRatesForCantor();
}
