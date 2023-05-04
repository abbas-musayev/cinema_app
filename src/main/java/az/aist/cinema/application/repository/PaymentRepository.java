package az.aist.cinema.application.repository;

import az.aist.cinema.application.entity.PaymentEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEnt,Long> {
    Optional<PaymentEnt> getByTransactionNumber(String transactionNumber);
}
