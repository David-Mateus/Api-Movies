package com.example.movies.repository;

import com.example.movies.models.MoviesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<MoviesModel,Long> {
}
