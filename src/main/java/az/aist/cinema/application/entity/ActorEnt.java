package az.aist.cinema.application.entity;

import az.aist.cinema.application.enums.Gender;
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
@Table(name = "actor")
public class ActorEnt extends CoreEnt{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "surname")
    String surname;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    Gender gender;

}
