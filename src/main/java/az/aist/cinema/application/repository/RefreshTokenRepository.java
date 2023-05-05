package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.RefreshTokenEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEnt,Long> {

    Optional<RefreshTokenEnt> findByToken(String token);
}
