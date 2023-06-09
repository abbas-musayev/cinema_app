package az.aist.cinema.application.entity;

import az.aist.cinema.application.config.StatusConverter;
import az.aist.cinema.application.enums.Status;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
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
    @Convert(converter = StatusConverter.class)
    protected Status status;

    @Column(name = "is_deleted")
    protected Boolean isDeleted;


    @PrePersist
    public void init(){
        status= Status.ACTIVE;
        isDeleted=false;
    }
}
