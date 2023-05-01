package az.aist.cinema.application.entity;

import az.aist.cinema.application.enums.Status;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@MappedSuperclass
public class CoreEnt {

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "update_date")
    @CreationTimestamp
    private LocalDateTime updateDate;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
