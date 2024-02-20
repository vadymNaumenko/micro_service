package processing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import processing.dto.AccountDTO;
import processing.dto.NewAccountDTO;
import processing.dto.PutAccountMoneyDTO;
import processing.service.AccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/processing")
public class ProcessingController {

    private final AccountService accountService;
    @PostMapping("/account")
    public AccountDTO createAccount(@RequestBody NewAccountDTO dto){
        return accountService.createNewAccount(dto);
    }
    @PutMapping("/put")
    public AccountDTO putMoney(@RequestBody PutAccountMoneyDTO accountMoneyDTO){
        return accountService.addMoneyAccount(accountMoneyDTO);
    }
}
