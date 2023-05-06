package az.aist.cinema.application.controller;

import az.aist.cinema.application.dto.SearchCriteria;
import az.aist.cinema.application.dto.session.SessionRequestDto;
import az.aist.cinema.application.dto.session.SessionResponseDto;
import az.aist.cinema.application.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/session")
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<Void> createSession(@RequestBody SessionRequestDto request){
        sessionService.create(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<SessionResponseDto>> getAllSession(){
        return ResponseEntity.ok(sessionService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<SessionResponseDto>> searchSession(@RequestBody List<SearchCriteria> searchCriteria){
        return ResponseEntity.ok(sessionService.search(searchCriteria));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSession(Long id){
        sessionService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionResponseDto> updateSession(@PathVariable("id") Long id,@RequestBody SessionRequestDto request){
        return ResponseEntity.ok(sessionService.edit(request,id));
    }



}
