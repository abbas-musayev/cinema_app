package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.PaymentEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEnt,Long> {
}
