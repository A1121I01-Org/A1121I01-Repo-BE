package module6.backend.repository;

import module6.backend.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE account SET password = :password WHERE account_id = :id", nativeQuery = true)
    void updatePassword(@Param("password") String password, @Param("id") Long id);
}
