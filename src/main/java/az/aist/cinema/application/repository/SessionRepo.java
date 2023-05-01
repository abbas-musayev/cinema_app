package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.SessionEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<SessionEnt,Long> {
}
