package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.ActorEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<ActorEnt,Long> {
}
