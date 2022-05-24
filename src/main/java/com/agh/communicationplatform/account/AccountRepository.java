package com.agh.communicationplatform.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsAccountByActivationToken(UUID activationToken);

    Optional<Account> findByUserId(Long userId);

    @Query(value = "SELECT * FROM account " +
                        "JOIN user ON account.user_id = user.user_id " +
                            "WHERE email = :email",
            nativeQuery = true)
    Optional<Account> getAccountByUserEmail(@Param("email") String email);

    @Modifying
    @Query(value = "UPDATE account SET state = 'CONFIRMED' " +
                        "WHERE activation_token = :activationToken",
            nativeQuery = true)
    void activateAccount(@Param("activationToken") UUID activationToken);
}
