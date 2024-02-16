package com.currency.service;

import com.currency.client.PV24CurrencyRateClient;
import com.currency.entity.ExchangeRate;
import com.currency.entity.PrivatBank;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PV24Service {

    private final PV24CurrencyRateClient client;

    private final Cache<LocalDate, Map<String, BigDecimal>> cache = CacheBuilder.newBuilder().build();


    public BigDecimal requestByCurrencyCode(String code) {
        try {
            return cache.get(LocalDate.now(), this::callAllByCurrentDate).get(code);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, BigDecimal> callAllByCurrentDate() {
        String json = client.requestByDate(LocalDate.now().minusDays(1L)); // todo must make if time not is 0000
        PrivatBank privatBank = unmarshall(json);
        System.out.println();
        return privatBank
                .getExchangeRate()
                .stream()
//                .collect(Collectors.toMap(ExchangeRate::getCurrency,item-> parserWithLocale(item.toString()))); //todo mast be all objects
                .collect(Collectors
                        .toMap(ExchangeRate::getCurrency,item-> parserWithLocale(String.valueOf(item.getSaleRateNB()))));
    }

    private BigDecimal parserWithLocale(String currency) {
        try {
            double v = NumberFormat.getNumberInstance(Locale.getDefault()).parse(currency).doubleValue();
            return BigDecimal.valueOf(v);
        } catch (ParseException e) {
            log.error("In class PV24Service method parserWithLocale");
            throw new RuntimeException(e);
        }
    }

    private PrivatBank unmarshall(String json) {
        try {
//            StringReader reader = new StringReader(json);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, PrivatBank.class);
        } catch (JsonProcessingException e) {
            log.error("In class PV24Service, method unmarshall()" );
            throw new RuntimeException("Ошибка при разборе JSON: " + e.getMessage(), e);
        }
    }

}
