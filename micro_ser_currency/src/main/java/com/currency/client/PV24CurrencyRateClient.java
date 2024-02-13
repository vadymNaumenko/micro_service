package com.currency.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component
public class PV24CurrencyRateClient implements HttpCurrencyDateRateClient {
//    https://dou.ua/forums/topic/17511/  //todo forum
//    https://minfin.com.ua/currency/banks/usd/ //todo mast be parser, this site have all curs banks Ukraine. minfin

    //    https://bank.gov.ua/ua/open-data/api-dev //todo api nasional bank Ukraine
//    https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5 //todo real time?
//    https://api.privatbank.ua/#p24/exchangeArchive
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Value("${banks.private}")
    private String base_url;

    @Override
    public String requestByDate(LocalDate date) {
        HttpClient client = HttpClient.newHttpClient();
        String url = buildUrlRequest(base_url, date);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildUrlRequest(String baseUrl, LocalDate date) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("date", DATE_TIME_FORMATTER.format(date)).build().toString();
    }

}
