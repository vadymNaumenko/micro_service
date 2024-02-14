package processing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "account")
public class Account {
    @Id
    @SequenceGenerator(name = "account_generator",sequenceName = "ACCOUNT_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "account_generator",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "currency_code", length = 6, nullable = false)
    private String currencyCode;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;
}
