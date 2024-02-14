package processing.dto;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public class AccountDTO {
    private Long userId;
    private String currencyCode;
    private BigDecimal balance;

}
