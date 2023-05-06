package az.aist.cinema.application.entity;

import az.aist.cinema.application.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "account")
public class AccountEnt extends CoreEnt{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<AuthorityEnt> authorities;

    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    BalanceEnt balance;

    @Column(name = "uuid")
    String uuid;

    @PrePersist
    private void prePersist(){
        UUID uuid= UUID.randomUUID();
        this.uuid = uuid.toString();
        status=Status.ACTIVE;
        isDeleted=false;
    }
}
