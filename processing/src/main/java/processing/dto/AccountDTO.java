package processing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@AllArgsConstructor
public class AccountDTO implements AccountFilter{
    private Long userId;
    private String currency;
    private BigDecimal balance;

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }
}
