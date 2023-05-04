package az.aist.cinema.application.entity;

import az.aist.cinema.application.enums.Genres;
import az.aist.cinema.application.enums.LanguageEnum;
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
@Table(name = "movie")
@SQLDelete(sql = "update movie set is_deleted = true , is_active = false where id = ?")
@Where(clause = "is_deleted = false")
public class MovieEnt extends CoreEnt{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "title")
    String title;

    @Column(name = "overview")
    String overview;

    @Column(name = "rating")
    String rating;

    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    LanguageEnum language;

    @Column(name = "fk_movie_cast")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<MovieCastEnt> movieCast;

    @Column(name = "genres")
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"))
    List<Genres> genres;

}
