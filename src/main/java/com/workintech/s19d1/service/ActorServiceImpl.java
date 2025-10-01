package com.workintech.s19d1.service;

import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.repository.ActorRepository;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.exceptions.ApiException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActorServiceImpl implements ActorService{

    private ActorRepository actorRepository;

    @Override
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }


    @Override
    public Actor findById(Long id) {
        return actorRepository.findById(id).orElseThrow(()->new ApiException("Actor is not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public void delete(Actor actor) {
        actorRepository.delete(actor);
    }

    @Override
    public Actor update(Long id, Actor actor) {
        Actor actorMovie = findById(id);
        actor.setId(id);
        return actorRepository.save(actor);
    }

}
