package az.aist.cinema.application.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "ticket")
public class TicketEnt extends CoreEnt{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String ticketNumber;

    String sector;

    String rows;

    String place;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    SessionEnt session;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    CustomerEnt customer;

}
