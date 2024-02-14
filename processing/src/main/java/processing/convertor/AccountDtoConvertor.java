package processing.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import processing.dto.AccountDTO;
import processing.entity.Account;

@Component
@RequiredArgsConstructor
public class AccountDtoConvertor {

    private final ModelMapper modelMapper;

    public AccountDTO ToAccountDTO(Account account){
        return modelMapper.map(account,AccountDTO.class);
    }

}
