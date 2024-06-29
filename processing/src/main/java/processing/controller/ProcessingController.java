package processing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import processing.dto.*;
import processing.service.AccountService;
import processing.service.ExchangeService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/processing")
public class ProcessingController {

    private final AccountService accountService;
    private final ExchangeService exchangeService;

    @PostMapping("/account")
    public AccountDTO createAccount(@RequestBody NewAccountDTO dto) {
        return accountService.createNewAccount(dto);
    }

    @PutMapping("/put")
    public AccountDTO putMoney(@RequestBody PutAccountMoneyDTO accountMoneyDTO) {
        return accountService.addMoneyAccount(accountMoneyDTO);
    }

    //    @PutMapping("/put/{uid}")
//    public BigDecimal exchange(@PathVariable("uid") String uid, @RequestBody ExchangeMoneyDTO moneyDTO){
//        exchangeService.exchangeCurrency(uid,m)
//    }
    @PutMapping("/exchange")
    public BigDecimal exchange(@RequestBody ExchangeMoneyDTO exchangeMoneyDTO) {
        return exchangeService.exchangeCurrency(
                exchangeMoneyDTO.getExchangeUid(),
                exchangeMoneyDTO.getFromAccountId(),
                exchangeMoneyDTO.getToAccountId(),
                exchangeMoneyDTO.getMoney());
    }

    @GetMapping("/account/{id}")
    public List<AccountFilter> getAccount (@PathVariable Long id){
        return accountService.findAllById(id);
    }

}
