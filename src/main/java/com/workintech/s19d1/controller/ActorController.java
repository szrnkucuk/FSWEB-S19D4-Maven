package com.workintech.s19d1.controller;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actor")
    public class ActorController {

    private final ActorService actorService;
    private final MovieService movieService;

    public ActorController(ActorService actorService, MovieService movieService) {
        this.actorService = actorService;
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() {
        return ResponseEntity.ok(actorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable Long id) {
        return ResponseEntity.ok(actorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Actor> createActorWithMovie(@RequestBody Actor actor) {
        List<Movie> savedMovies = actor.getMovies().stream()
                .map(movieService::save)
                .toList();
        actor.setMovies(savedMovies);
        return ResponseEntity.ok(actorService.save(actor));
    }

    // [PUT] /workintech/actors/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable Long id, @RequestBody Actor updatedActor) {
        return ResponseEntity.ok(actorService.update(id, updatedActor));
    }

    // [DELETE] /workintech/actors/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
        Actor actor = actorService.findById(id);
        actorService.delete(actor);
        return ResponseEntity.noContent().build();
    }
}
