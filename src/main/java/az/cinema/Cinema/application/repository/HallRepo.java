package az.cinema.Cinema.application.repository;

import az.cinema.Cinema.application.entity.HallEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepo extends JpaRepository<HallEnt,Long> {
}
