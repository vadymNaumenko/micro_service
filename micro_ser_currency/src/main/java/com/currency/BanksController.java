package com.currency;

import com.currency.service.PV24Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("currency")
public class BanksController {

    private final PV24Service pv24Service;

    @GetMapping("/privat_bank")
    public BigDecimal getCurrencyFromPrivatBank(){
        return pv24Service.requestByCurrencyCode("UA");
    }
}
