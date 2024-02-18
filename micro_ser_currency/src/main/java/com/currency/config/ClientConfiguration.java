package com.currency.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "client.config")
@Data
public class ClientConfiguration {
    private String privateBankUrl;
    private String monoBankUrl;
}
