package processing.dto;

import java.math.BigDecimal;

public interface AccountFilter {
     Long getUserId();
     String getCurrency();
     BigDecimal getBalance();
}
