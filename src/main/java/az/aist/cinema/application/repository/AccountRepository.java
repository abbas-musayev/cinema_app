package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.AccountEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEnt,Long> {
    AccountEnt findByUuid(String accountUuid);
}
