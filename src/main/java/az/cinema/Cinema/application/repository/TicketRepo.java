package az.cinema.Cinema.application.repository;

import az.cinema.Cinema.application.entity.TicketEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepo extends JpaRepository<TicketEnt,Long> {
}
