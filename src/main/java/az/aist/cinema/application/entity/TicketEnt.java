package az.aist.cinema.application.entity;

import az.aist.cinema.application.enums.TicketStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "ticket")
public class TicketEnt extends CoreEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "ticket_number")
    String ticketNumber;

    @Column(name = "sector")
    String sector;

    @Column(name = "line")
    String line;

    @Column(name = "place")
    String place;

    @Column(name = "amount")
    BigDecimal amount;

    @Column(name = "ticket_status")
    @Enumerated(EnumType.ORDINAL)
    TicketStatus ticketStatus;  // tarix meselesi sorusu, bron tarixi ucun tickerleri release ede bilsin

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    SessionEnt session;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    CustomerEnt customer;

}
