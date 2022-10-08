package module6.backend.repository;

import module6.backend.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "select * from account where account_flag = 0 ", nativeQuery = true)
    List<Account> findAllAccount();
}
