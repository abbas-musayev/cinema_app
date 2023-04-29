package az.cinema.Cinema.application.repository;

import az.cinema.Cinema.application.entity.MovieEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<MovieEnt,Long> {
}
