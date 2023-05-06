package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.AccountEnt;
import az.aist.cinema.application.entity.AuthorityEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEnt,Long> {
}
