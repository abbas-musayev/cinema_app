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
    protected LocalDateTime createDate;

    @Column(name = "update_date")
    @CreationTimestamp
    protected LocalDateTime updateDate;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    protected Status status;

    @Column(name = "is_deleted")
    protected Boolean isDeleted;
}
