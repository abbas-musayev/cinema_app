package az.aist.cinema.application.entity;

import az.aist.cinema.application.enums.Valute;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "balance")
public class BalanceEnt extends CoreEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "balance")
    BigDecimal balance;

    @Column(name = "valute")
    @Enumerated(EnumType.STRING)
    Valute valute;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    AccountEnt account;

}
