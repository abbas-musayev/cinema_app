package az.aist.cinema.application.entity;

import az.aist.cinema.application.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "account")
public class AccountEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    Byte[] password;

    @Column(name = "role")
    Role role;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    BalanceEnt balance;

    @Column(name = "uuid")
    String uuid;

    @PrePersist
    private void prePersist(){
        UUID uuid= UUID.randomUUID();
        this.uuid = uuid.toString();
    }

}
