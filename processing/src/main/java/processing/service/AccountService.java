package processing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import processing.convertor.AccountDtoConvertor;
import processing.dto.AccountDTO;
import processing.dto.AccountFilter;
import processing.dto.NewAccountDTO;
import processing.dto.PutAccountMoneyDTO;
import processing.entity.Account;
import processing.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountDtoConvertor accountConvertor;

    @Transactional
    public AccountDTO createNewAccount(NewAccountDTO dto) {
        Account account = new Account();
        account.setBalance(new BigDecimal(0));
        account.setCurrencyCode(dto.getCurrencyCode());
        account.setUserId(dto.getUserId());

        return accountConvertor
                .ToAccountDTO(accountRepository.save(account));
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public AccountDTO addMoneyAccount(PutAccountMoneyDTO putAccountMoneyDTO) {
        Optional<Account> account = accountRepository.findById(putAccountMoneyDTO.getAccountId());
        Account res = account.map(acc -> {
                    var balance = acc.getBalance().add(putAccountMoneyDTO.getMoney());
                    acc.setBalance(balance);
                    return accountRepository.save(acc);
                })
                .orElseThrow(() -> new IllegalArgumentException("Account with ID " + putAccountMoneyDTO.getAccountId() + " not found"));

        return accountConvertor.ToAccountDTO(res);
    }

    public Account findById(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found with id:" + accountId));
    }

    public List<AccountFilter> findAllById(Long id) {
         return accountRepository.findAllAccountByUserId(id);
    }
}
