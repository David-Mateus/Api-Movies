package com.example.movies.services;

import com.example.movies.models.MoviesModel;
import com.example.movies.models.UserModel;
import com.example.movies.repository.MoviesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoviesService {
    @Autowired
    private MoviesRepository moviesRepository;
    @Autowired
    private UserService userService;

    public MoviesModel findById(Long id){
        Optional<MoviesModel> movies = this.moviesRepository.findById(id);
        return movies.orElseThrow(() -> new RuntimeException(
                "Tarefa não encontrada"
        ));
    }
    @Transactional
    public MoviesModel create(MoviesModel obj){
        UserModel user = this.userService.findyById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.moviesRepository.save(obj);
        return obj;

    }
    @Transactional
    public MoviesModel update(MoviesModel obj){
        MoviesModel newObj = findById(obj.getId());
        newObj.setName(obj.getName());
        newObj.setImage(obj.getImage());
        newObj.setDescription(obj.getDescription());
        return  this.moviesRepository.save(newObj);
    }
    public void delete(Long id){
        findById(id);
        try {
            this.moviesRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Não é possivel relacionar");
        }
    }
}
