package com.workintech.s19d1.controller;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ActorService actorService;

    public MovieController(MovieService movieService, ActorService actorService) {
        this.movieService = movieService;
        this.actorService = actorService;
    }

    // [GET] /workintech/movies
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.findAll());
    }

    // [GET] /workintech/movies/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    // [POST] /workintech/movies
    @PostMapping
    public ResponseEntity<Movie> createMovieWithActor(@RequestBody Movie movie) {
        // İçindeki actor'ları önce kaydet
        List<Actor> savedActors = movie.getActors().stream()
                .map(actorService::save)
                .toList();
        movie.setActors(savedActors);
        return ResponseEntity.ok(movieService.save(movie));
    }

    // [PUT] /workintech/movies/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie updatedMovie) {
        return ResponseEntity.ok(movieService.update(id, updatedMovie));
    }

    // [DELETE] /workintech/movies/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        Movie movie=movieService.findById(id);
        movieService.delete(movie);
        return ResponseEntity.noContent().build();
    }
}
