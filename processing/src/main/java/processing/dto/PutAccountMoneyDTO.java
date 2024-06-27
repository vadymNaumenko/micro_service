package processing.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PutAccountMoneyDTO {
    @JsonAlias("uid")
    private String uid;
    @JsonAlias("account")
    private Long accountId;
    @JsonAlias("money")
    private BigDecimal money;
}
