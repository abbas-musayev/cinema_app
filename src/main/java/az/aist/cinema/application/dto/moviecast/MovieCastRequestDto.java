package az.aist.cinema.application.dto.moviecast;

import az.aist.cinema.application.dto.actor.ActorRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieCastRequestDto {
    Set<ActorRequestDto> actors;
}
