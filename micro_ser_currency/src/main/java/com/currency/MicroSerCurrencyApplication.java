package com.currency;

import com.currency.client.PV24CurrencyRateClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MicroSerCurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroSerCurrencyApplication.class, args);
        System.out.println("start");
        PV24CurrencyRateClient client = new PV24CurrencyRateClient();
        client.requestByCurrencyCode("UA");
        System.out.println("d");
    }


}
