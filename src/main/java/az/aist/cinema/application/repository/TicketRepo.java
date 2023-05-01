package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.TicketEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepo extends JpaRepository<TicketEnt,Long> {
}
