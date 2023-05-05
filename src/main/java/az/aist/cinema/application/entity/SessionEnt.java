package az.aist.cinema.application.entity;

import az.aist.cinema.application.enums.SeansTime;
import az.aist.cinema.application.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "session")
@SQLDelete(sql = "update session set is_deleted = true , is_active = false where id = ?")
@Where(clause = "is_deleted = false")
public class SessionEnt extends CoreEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "session_name")
    String name;

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "start_hour")
    String startHour;

    @Column(name = "end_date")
    LocalDate endDate;

    @Column(name = "end_hour")
    String end_hour;

    @Column(name = "price")
    String price;

    @Column(name = "seans_time")
    @Enumerated(EnumType.STRING)
    SeansTime seansTime;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
            @JoinColumn(name = "fk_movie",referencedColumnName = "id")
    MovieEnt movie;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<HallEnt> hall;


    @PrePersist
    public void init(){
        status= Status.ACTIVE;
        isDeleted=false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SessionEnt that = (SessionEnt) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
