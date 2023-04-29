package az.cinema.Cinema.application.entity;

import az.cinema.Cinema.application.enums.Role;
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
@Table(name = "user_account")
public class AccountEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username")
    String username;

    @Column(name = "nickname")
    String nickname;

    @Column(name = "password")
    Byte[] password;

    @Column(name = "role")
    Role role;

}
