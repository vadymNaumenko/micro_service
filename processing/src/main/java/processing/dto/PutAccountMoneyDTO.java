package processing.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PutAccountMoneyDTO {
    @JsonAlias("uid")
    private String uid;
    @JsonAlias("account")
    private Long accountId;
    @JsonAlias("money")
    private BigDecimal money;
}
