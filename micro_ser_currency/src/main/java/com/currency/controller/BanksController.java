package com.currency.controller;

import com.currency.service.PV24Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("currency")
public class BanksController {

    private final PV24Service pv24Service;

    @GetMapping("/privat_bank/{currency}")
    public BigDecimal getCurrencyFromPrivatBank(@PathVariable String currency){
        return pv24Service.requestByCurrencyCode(currency.toUpperCase());
    }
}
