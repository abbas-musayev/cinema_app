package az.aist.cinema.application.entity;

import az.aist.cinema.application.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@SQLDelete(sql = "update movie_cast set is_deleted = true , is_active = false where id = ?")
@Where(clause = "is_deleted = false")
public class MovieCastEnt extends CoreEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "fk_actor")
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<ActorEnt> actorEnts;

    @PrePersist
    public void init(){
        status= Status.ACTIVE;
        isDeleted=false;
    }
}
