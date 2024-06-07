package com.currency.monitor.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Main class for other currencies. This is super class
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@ToString
public abstract class BasicCurrency {
    private String code;
    private String currency;
}
