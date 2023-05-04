package az.aist.cinema.application.dto.actor;

import az.aist.cinema.application.enums.ActorRole;
import az.aist.cinema.application.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActorRequestDto {
    String name;
    String surname;
    Gender gender;
    ActorRole actorRole;
}
