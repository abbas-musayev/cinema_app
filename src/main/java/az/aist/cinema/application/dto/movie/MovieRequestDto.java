package az.aist.cinema.application.dto.movie;

import az.aist.cinema.application.dto.moviecast.MovieCastRequestDto;
import az.aist.cinema.application.entity.MovieCastEnt;
import az.aist.cinema.application.enums.Genres;
import az.aist.cinema.application.enums.LanguageEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieRequestDto {
    String name;
    String title;
    String overview;
    String rating;
    LanguageEnum language;
    List<MovieCastRequestDto> movieCast;
    List<Genres> genres;
}
