package az.aist.cinema.application.entity;

import az.aist.cinema.application.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "customer")
public class CustomerEnt extends CoreEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "surname")
    String surname;

    @Column(name = "middle_name")
    String middleName;

    @Column(name = "email")
    String email;

    @Column(name = "birthday")
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    LocalDate localDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_account")
    AccountEnt accountEnt;

    @PrePersist
    public void init(){
        status= Status.ACTIVE;
        isDeleted=false;
    }
}
