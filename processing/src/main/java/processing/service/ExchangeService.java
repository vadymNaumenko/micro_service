package processing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import processing.dto.PutAccountMoneyDTO;
import processing.entity.Account;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangeService {

    private static final String CURRENCY_UA = "UAH";
    private final CurrencyService currencyService;
    private final AccountService accountService;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BigDecimal exchangeCurrency(String uuid, Long fromAccount, Long toAccount, BigDecimal amount) {
        Account source = accountService.findById(fromAccount);
        Account target = accountService.findById(toAccount);

        log.info("Exchange operation {} from account {} to account {} started", uuid, fromAccount, toAccount);

        BigDecimal result;
        if (!CURRENCY_UA.equals(source.getCurrencyCode()) && CURRENCY_UA.equals(target.getCurrencyCode())) {
            BigDecimal rate = currencyService.loadCurrencyRate(source.getCurrencyCode());
            result = exchangeWithMultiply(uuid, source, target, rate, amount);
        } else if (CURRENCY_UA.equals(source.getCurrencyCode()) && !CURRENCY_UA.equals(target.getCurrencyCode())) {
            BigDecimal rate = currencyService.loadCurrencyRate(target.getCurrencyCode());
            BigDecimal multiplier = new BigDecimal(1).divide(rate, 6, RoundingMode.DOWN); // or 4
            result = exchangeWithMultiply(uuid, source, target, multiplier, amount);
        } else if (!CURRENCY_UA.equals(source.getCurrencyCode()) && !CURRENCY_UA.equals(target.getCurrencyCode())) {
            BigDecimal rateFrom = currencyService.loadCurrencyRate(source.getCurrencyCode());
            BigDecimal rateTo = currencyService.loadCurrencyRate(target.getCurrencyCode());
            result = exchangeThroughUa(uuid, source, target, rateFrom, rateTo, amount);
        } else if (CURRENCY_UA.equals(source.getCurrencyCode()) && CURRENCY_UA.equals(target.getCurrencyCode())) {
            result = simpleExchange(uuid,source,target,amount);
        } else {
            throw new IllegalArgumentException("Unknown behavior");
        }

        return result;
    }


    private BigDecimal simpleExchange(String uuid, Account source, Account target, BigDecimal amount) {
        accountService.addMoneyAccount(new PutAccountMoneyDTO(uuid,source.getId(),amount.negate()));
        accountService.addMoneyAccount(new PutAccountMoneyDTO(uuid,source.getId(),amount));
        return amount;
    }

    private BigDecimal exchangeWithMultiply(String uuid, Account source, Account target, BigDecimal rate, BigDecimal amount) {
        accountService.addMoneyAccount(new PutAccountMoneyDTO(uuid, source.getId(), amount.negate()));
        BigDecimal result = amount.multiply(rate);
        accountService.addMoneyAccount(new PutAccountMoneyDTO(uuid, target.getId(), result));
        return result;
    }
    private BigDecimal exchangeThroughUa(String uuid, Account source, Account target, BigDecimal rateFrom, BigDecimal rateTo, BigDecimal amount) {
        BigDecimal ua = amount.multiply(rateFrom);
        BigDecimal result = ua.divide(rateTo,4,RoundingMode.HALF_DOWN); // 4
        accountService.addMoneyAccount(new PutAccountMoneyDTO(uuid,target.getId(),result));
        return result;
    }





}
