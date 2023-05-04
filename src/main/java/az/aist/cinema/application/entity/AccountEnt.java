package az.aist.cinema.application.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

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
public class AccountEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    Byte[] password;

    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<Authority> authorities;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    BalanceEnt balance;

    @Column(name = "uuid")
    String uuid;

    @CreationTimestamp
    LocalDateTime localDateTime;

    @Column(name = "is_active")
    Boolean isActive;

    @Column(name = "is_deleted")
    Boolean isDeleted;

    @PrePersist
    private void prePersist(){
        UUID uuid= UUID.randomUUID();
        this.uuid = uuid.toString();
    }

//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
}
