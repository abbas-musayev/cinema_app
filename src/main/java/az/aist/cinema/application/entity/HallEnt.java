package az.aist.cinema.application.entity;

import az.aist.cinema.application.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "hall")
@SQLDelete(sql = "update hall set is_deleted = true , is_active = false where id = ?")
@Where(clause = "is_deleted = false")
public class HallEnt extends CoreEnt{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "seat_count")
    Integer seatCount;

    @PrePersist
    public void init(){
        status= Status.ACTIVE;
        isDeleted=false;
    }
}
