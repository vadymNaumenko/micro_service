package com.currency.client;


import com.currency.entity.PrivatBank;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class PV24CurrencyRateClient implements HttpCurrencyDateRateClient {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final String base_url = "https://api.privatbank.ua/p24api/exchange_rates?json";
    @Override
    public String requestByDate(LocalDate date) {
        HttpClient client = HttpClient.newHttpClient();
        String url = buildUrlRequest(base_url,date);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        try {
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildUrlRequest(String baseUrl, LocalDate date) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("date",DATE_TIME_FORMATTER.format(date)).build().toString();
    }

    ///////////////////////////////////////////////////////

    private final Cache<LocalDate, Map<String, BigDecimal>> cache = CacheBuilder.newBuilder().build();


    public BigDecimal requestByCurrencyCode(String code){
        try {
            return cache.get(LocalDate.now(),this::callAllByCurrentDate).get(code);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, BigDecimal> callAllByCurrentDate() {
        String xml = this.requestByDate(LocalDate.now().minusDays(1L));
        PrivatBank privatBank = unmarshall(xml);
        System.out.println();
        return null;
    }

    private PrivatBank unmarshall(String xml){
        try (StringReader reader = new StringReader(xml)){
            JAXBContext context = JAXBContext.newInstance(PrivatBank.class);
            return (PrivatBank) context.createUnmarshaller().unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
