package processing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final RestTemplate restClient;
    @Value("${service.currency.url}")
    private String currencyUrl;

    public BigDecimal loadCurrencyRate(String currency){
return restClient.getForObject(currencyUrl+"/privat_bank/{currency}", BigDecimal.class,currency);
    }

}
