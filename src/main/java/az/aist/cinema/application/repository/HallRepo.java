package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.HallEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepo extends JpaRepository<HallEnt,Long> {
}
