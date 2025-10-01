package com.workintech.s19d1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "actor", schema = "fsweb")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name="first_name")
    private String firstName;
    @Column (name="last_name")
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column (name="birth_date")
    private LocalDate birthDate;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "movie_actor", schema="fsweb", inverseJoinColumns = @JoinColumn(name = "actor_id"),
            joinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movies;

    public  void addMovie(Movie movie){
        if(movies==null){
            movies=new ArrayList<>();
        } movies.add(movie);
    }
}
