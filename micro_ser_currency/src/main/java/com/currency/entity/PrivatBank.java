package com.currency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrivatBank {
    private LocalDate date;
    private String bank;
    private int baseCurrency;
    private List<ExchangeRate> exchangeRate;

}
