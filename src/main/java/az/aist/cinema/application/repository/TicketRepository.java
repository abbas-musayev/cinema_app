package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.TicketEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEnt,Long> {
    @Query("select t from TicketEnt t where t.ticketNumber in (?1)")
    List<TicketEnt> findByTicketNumber(List<String> ticketNumber);
}
