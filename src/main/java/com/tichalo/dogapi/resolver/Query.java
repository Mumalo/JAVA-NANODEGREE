package com.tichalo.dogapi.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tichalo.dogapi.entities.Dog;
import com.tichalo.dogapi.exceptions.DogNotFoundException;
import com.tichalo.dogapi.repositories.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    private DogRepository dogRepository;

    public Query(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> findAllDogs(){
        return dogRepository.findAll();
    }

    public Dog findDogById(Long id){
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if (optionalDog.isPresent()){
            return optionalDog.get();
        } else {
            throw  new DogNotFoundException("Dog Not Found", id);
        }
    }
}
