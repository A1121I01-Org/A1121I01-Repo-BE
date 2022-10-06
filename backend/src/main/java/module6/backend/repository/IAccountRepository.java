package module6.backend.repository;

import module6.backend.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "SELECT * FROM account WHERE username=?1", nativeQuery = true)
    Account findAccountByUsername(String username);
}
