package az.cinema.Cinema.application.repository;

import az.cinema.Cinema.application.entity.CustomerEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEnt,Long> {
}
