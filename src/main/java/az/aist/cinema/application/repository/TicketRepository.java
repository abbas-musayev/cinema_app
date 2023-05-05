package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.TicketEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<TicketEnt,Long>, JpaSpecificationExecutor<TicketEnt> {
    @Query("select t from TicketEnt t where t.ticketNumber in (?1)")
    Optional<List<TicketEnt>> findByTicketNumber(List<String> ticketNumber);


    @Query("select t from TicketEnt t where t.ticketNumber=?1")
    Optional<TicketEnt> findByTicketNumber(String ticketNumber);

}
