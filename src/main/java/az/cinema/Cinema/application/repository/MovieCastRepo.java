package az.cinema.Cinema.application.repository;

import az.cinema.Cinema.application.entity.MovieCastEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCastRepo extends JpaRepository<MovieCastEnt,Long> {
}
