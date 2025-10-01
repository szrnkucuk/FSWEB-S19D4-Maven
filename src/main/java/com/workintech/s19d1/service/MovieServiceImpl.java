package com.workintech.s19d1.service;

import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.repository.MovieRepository;
import com.workintech.s19d1.exceptions.ApiException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ApiException("Movie is not found"+id, HttpStatus.NOT_FOUND));
    }


    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Long id, Movie movie) {
        Movie foundMovie = findById(id);
        movie.setId(id);
        return movieRepository.save(movie);
    }

    @Override
    public void delete(Movie movie) {
        movieRepository.delete(movie);

    }
}
