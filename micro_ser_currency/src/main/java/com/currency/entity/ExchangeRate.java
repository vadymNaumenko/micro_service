package com.currency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRate {
    private String baseCurrency;
    private String currency;
    private double saleRateNB;
    private double purchaseRateNB;

}
