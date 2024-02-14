package processing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import processing.convertor.AccountDtoConvertor;
import processing.dto.AccountDTO;
import processing.dto.NewAccountDTO;
import processing.entity.Account;
import processing.repository.AccountRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountDtoConvertor accountConvertor;

    public AccountDTO createNewAccount(NewAccountDTO dto){
        Account account = new Account();
        account.setBalance(new BigDecimal(0));
        account.setCurrencyCode(dto.getCurrencyCode());
        account.setUserId(dto.getUserId());

        return accountConvertor
                .ToAccountDTO(accountRepository.save(account));
    }


}
