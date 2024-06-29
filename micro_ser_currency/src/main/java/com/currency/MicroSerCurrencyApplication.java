package com.currency;

import com.currency.config.ClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ClientConfiguration.class)
public class MicroSerCurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroSerCurrencyApplication.class, args);
    }

}
