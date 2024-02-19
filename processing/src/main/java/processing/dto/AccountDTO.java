package processing.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class AccountDTO {
    private Long user;
    private String currency;
    private BigDecimal balance;

}
