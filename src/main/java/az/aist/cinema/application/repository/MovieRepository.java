package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.MovieEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEnt,Long> , JpaSpecificationExecutor<MovieEnt> {
}
