package processing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import processing.dto.AccountDTO;
import processing.dto.NewAccountDTO;
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
}
