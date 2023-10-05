package com.example.movies.services;

import com.example.movies.models.UserModel;
import com.example.movies.repository.MoviesRepository;
import com.example.movies.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    //comunica com repository
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository, MoviesRepository moviesRepository) {
        this.userRepository = userRepository;
    }


    public UserModel findyById(Long id){
        Optional<UserModel> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
                "Usuario não encontrado! Id:"+id
        ));
    }
    @Transactional
    public UserModel create(UserModel obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);

        return obj;
    }
    @Transactional
    public UserModel update(UserModel obj){
        UserModel newObj = findyById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);

    }
    public void delete(Long id){
        findyById(id);
        try {
            this.userRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Não é possivel excluir pois há entidades relacionadas!");
        }
    }
}
