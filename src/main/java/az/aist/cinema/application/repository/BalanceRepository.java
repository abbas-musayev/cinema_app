package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.BalanceEnt;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceEnt,Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "balance")
    @Query("select b from AccountEnt acc join acc.balance b where acc.uuid= ?1 and acc.isDeleted=false and acc.isActive=true")
    Optional<BalanceEnt> findByAccountUuid(String uuid);
}
