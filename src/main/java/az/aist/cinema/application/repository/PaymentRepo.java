package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.PaymentEnt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<PaymentEnt,Long> {
}
