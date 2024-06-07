package com.currency.monitor.export.lineitem;

import java.io.IOException;

public interface JsonLineValue {

    JsonLine getJsonLineItemByURL() throws IOException ;

    JsonLine getJsonLine();

}
