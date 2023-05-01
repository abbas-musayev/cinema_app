package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.MovieCastEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCastRepo extends JpaRepository<MovieCastEnt,Long> {
}
