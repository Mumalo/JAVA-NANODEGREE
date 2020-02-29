package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    PetService petService;

    public Pet getById(Long id) {
        Optional<Pet> petOptional = petRepository.findById(id);
        return petOptional.get();
    }

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet findById(Long petId) {
        Optional<Pet> petOptional = petRepository.findById(petId);
        return petOptional.orElseThrow(() -> new AssertionError("pet does not exists"));
    }

    public List<Pet> listPets() {
        return (List<Pet>) petRepository.findAll();
    }

    public List<Pet> findAllByowner(Customer owner) {
        return petRepository.findAllByOwner(owner);
    }

    public Customer getPetOwner(Long petId) {
        Pet thePet = petService.getById(petId);
        return thePet.getOwner();
    }

    public List<Pet> findAllPetsInList(List<Long> petIds) {
        return petIds.stream()
                .map(this::findById)
                .collect(Collectors.toList());
    }


}
