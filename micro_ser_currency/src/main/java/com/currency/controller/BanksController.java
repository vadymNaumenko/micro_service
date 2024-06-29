package com.currency.controller;

import com.currency.service.PV24Service;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("currency")
@Slf4j
public class BanksController {

    private final PV24Service pv24Service;

    @GetMapping("/privat_bank/{currency}")
    public BigDecimal getCurrencyFromPrivatBank(@PathVariable String currency, HttpServletRequest httpServletRequest){
      log.info("method getCurrencyFromPrivatBank. Get currency: {}. adres: {}",currency,httpServletRequest.getRemoteAddr());
        return pv24Service.requestByCurrencyCode(currency.toUpperCase());
    }
}
