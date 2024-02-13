package com.currency.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonoBankCurrency {
//    api.monobank.ua/bank/currency //todo mono bank
//    https://dou.ua/forums/topic/17511/  //todo forum
//    https://index.minfin.com.ua/reference/currency/code/  //code currency


    private int currencyCodeA;
    private int currencyCodeB;
    private long date;
    private double rateBuy;
    private double rateSell;
}
