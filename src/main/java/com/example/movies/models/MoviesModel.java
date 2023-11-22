package com.example.movies.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "movies_tb")
public class MoviesModel {
    // Image, Nome and description
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // cria relação entre as tabelas
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    private String image;
    private String name;
    private String description;

    public MoviesModel() {

    }

    public MoviesModel(Long id, String image, String name, String description) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
