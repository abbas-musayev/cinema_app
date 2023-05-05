package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.SessionEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<SessionEnt,Long> {

    List<SessionEnt> findByMovieId(String movieId);
}
