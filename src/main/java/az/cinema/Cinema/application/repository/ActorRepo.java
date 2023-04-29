package az.cinema.Cinema.application.repository;

import az.cinema.Cinema.application.entity.ActorEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepo extends JpaRepository<ActorEnt,Long> {
}
