package az.cinema.Cinema.application.repository;

import az.cinema.Cinema.application.entity.SessionEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<SessionEnt,Long> {
}
