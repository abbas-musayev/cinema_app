package az.aist.cinema.application.entity;

import az.aist.cinema.application.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
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
    Byte[] password;

    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<AuthorityEnt> authorities;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
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
