package az.aist.cinema.application.controller;

import az.aist.cinema.application.dto.SearchCriteria;
import az.aist.cinema.application.dto.movie.MovieRequestDto;
import az.aist.cinema.application.dto.movie.MovieResponseDto;
import az.aist.cinema.application.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Void> createMovie(@RequestBody MovieRequestDto dto){
        movieService.saveMovie(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDto>> getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovie());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponseDto>> searchMovie(@RequestBody List<SearchCriteria> searchCriteria){
        return ResponseEntity.ok(movieService.getMovie(searchCriteria));
    }
}
