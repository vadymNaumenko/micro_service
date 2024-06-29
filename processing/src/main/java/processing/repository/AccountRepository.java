package processing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import processing.dto.AccountDTO;
import processing.dto.AccountFilter;
import processing.entity.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query(value = "SELECT user_id userId," +
                   " currency_code currency," +
                   " balance" +
                   " from  account" +
                   " WHERE user_id = :userId"
    ,nativeQuery = true)
    List<AccountFilter> findAllAccountByUserId(Long userId);
}
