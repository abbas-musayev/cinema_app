package az.aist.cinema.application.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "movie_cast")
public class MovieCastEnt extends CoreEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "fk_actor")
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<ActorEnt> actorEnts;
}
