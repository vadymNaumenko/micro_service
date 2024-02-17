package com.currency.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "client.config")
public class ClientConfiguration {
    private String privateBankUrl;
}
