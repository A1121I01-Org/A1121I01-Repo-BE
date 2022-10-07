package module6.backend.repository;

import module6.backend.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "SELECT * FROM account WHERE username=?1 and account_flag = 0", nativeQuery = true)
    Account findAccountByUsername(String username);

    @Query(value = "INSERT INTO account(username,password) values (?1,?2)", nativeQuery = true)
    void saveAccount(String username, String password);


    @Query(value = "UPDATE account as a set a.username=?1, a.password=?2 where a.account_id=?3", nativeQuery = true)
    Account updateAccount(String username, String password, Long accountId);
}
