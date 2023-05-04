package az.aist.cinema.application.entity;

import az.aist.cinema.application.enums.Valute;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "payment")
@SQLDelete(sql = "update payment set is_deleted = true , is_active = false where id = ?")
@Where(clause = "is_deleted = false")
public class PaymentEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "amount")
    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    Valute valute;

    @Column(name = "transaction_number")
    String transactionNumber;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "account_uuid", referencedColumnName = "uuid")
    AccountEnt accountEnt;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_number", referencedColumnName = "ticket_number")
    TicketEnt ticket;
}
