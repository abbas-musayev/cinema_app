package az.aist.cinema.application.controller;

import az.aist.cinema.application.dto.SearchCriteria;
import az.aist.cinema.application.dto.movie.MovieRequestDto;
import az.aist.cinema.application.dto.movie.MovieResponseDto;
import az.aist.cinema.application.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Void> createMovie(@RequestBody MovieRequestDto dto){
        movieService.create(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDto>> getAllMovies(){
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponseDto>> searchMovie(@RequestBody List<SearchCriteria> searchCriteria){
        return ResponseEntity.ok(movieService.search(searchCriteria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> editMovie(@RequestBody MovieRequestDto request,@PathVariable("id") Long id){
        return ResponseEntity.ok(movieService.edit(request,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(Long id){
        movieService.delete(id);
        return ResponseEntity.ok().build();
    }
}
