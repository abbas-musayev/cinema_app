package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.AccountEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEnt,Long> {
    @Query("select acc from AccountEnt acc where acc.uuid= ?1 and acc.isDeleted=false and acc.isActive=true")
    Optional<AccountEnt> findByUuid(String accountUuid);

    Optional<AccountEnt> findByUsername(String username);
}
