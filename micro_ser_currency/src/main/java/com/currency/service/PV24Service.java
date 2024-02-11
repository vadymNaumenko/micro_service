package com.currency.service;

import com.currency.client.PV24CurrencyRateClient;
import com.currency.entity.PrivatBank;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class PV24Service {

    private final PV24CurrencyRateClient client;

    private final Cache<LocalDate, Map<String,BigDecimal>> cache = CacheBuilder.newBuilder().build();


    public BigDecimal requestByCurrencyCode(String code){
        try {
            return cache.get(LocalDate.now(),this::callAllByCurrentDate).get(code);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, BigDecimal> callAllByCurrentDate() {
        String json = client.requestByDate(LocalDate.now().minusDays(1L));
        PrivatBank privatBank = unmarshall(json);
        System.out.println();
        return null;
    }

    private PrivatBank unmarshall(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, PrivatBank.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка при разборе JSON: " + e.getMessage(), e);
        }
    }

}
