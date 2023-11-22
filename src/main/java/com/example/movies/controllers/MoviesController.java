package com.example.movies.controllers;

import com.example.movies.models.MoviesModel;
import com.example.movies.services.MoviesService;
import com.example.movies.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movies")
@Validated
public class MoviesController {
    @Autowired
    private MoviesService moviesService;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<MoviesModel> findById(@PathVariable Long id){
        MoviesModel obj = this.moviesService.findById(id);
        return ResponseEntity.ok(obj);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MoviesModel>> findAllByUserId(@PathVariable Long userId){
        userService.findyById(userId);
        List<MoviesModel> obj = this.moviesService.findAllById(userId);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody MoviesModel obj){
        this.moviesService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public  ResponseEntity<Void> update(@Valid @RequestBody MoviesModel obj, @PathVariable Long id){
        obj.setId(id);
        this.moviesService.update(obj);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.moviesService.delete(id);
        return ResponseEntity.noContent().build();
    }
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<MoviesModel>> findAllById(@PathVariable Long userId){
//        List<MoviesModel> movies = this.moviesService.findAllById(userId);
//        return ResponseEntity.ok().body(movies);
//    }
}
