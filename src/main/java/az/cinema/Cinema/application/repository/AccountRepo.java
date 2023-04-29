package az.cinema.Cinema.application.repository;

import az.cinema.Cinema.application.entity.AccountEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<AccountEnt,Long> {
}
