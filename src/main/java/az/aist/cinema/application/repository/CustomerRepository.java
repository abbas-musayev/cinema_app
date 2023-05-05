package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.CustomerEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEnt,Long>, JpaSpecificationExecutor<CustomerEnt> {
}
