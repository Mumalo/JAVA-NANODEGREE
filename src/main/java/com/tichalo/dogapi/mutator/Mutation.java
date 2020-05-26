package com.tichalo.dogapi.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.tichalo.dogapi.entities.Dog;
import com.tichalo.dogapi.exceptions.BreedNotFoundException;
import com.tichalo.dogapi.exceptions.DogNotFoundException;
import com.tichalo.dogapi.repositories.DogRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {

    private DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public boolean deleteDogBreed(String breed){
        boolean deleted = false;
        Iterable<Dog> allDogs = dogRepository.findAll();

        for (Dog d: allDogs){
            if (d.getBreed().equals(breed)){
                dogRepository.delete(d);
                deleted = true;
            }
        }

        if (!deleted){
            throw new BreedNotFoundException("Breed not found", breed);
        }

        return deleted;
    }

    public Dog updateDogName(String newName, Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);

        if (optionalDog.isPresent()) {
            Dog dog = optionalDog.get();
            // Set the new name and save the updated dog
            dog.setName(newName);
            dogRepository.save(dog);
            return dog;
        } else {
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }
}
