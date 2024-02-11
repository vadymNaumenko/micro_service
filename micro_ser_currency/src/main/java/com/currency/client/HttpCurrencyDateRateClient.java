package com.currency.client;

import java.time.LocalDate;

public interface HttpCurrencyDateRateClient {
    public String requestByDate(LocalDate date);
}
