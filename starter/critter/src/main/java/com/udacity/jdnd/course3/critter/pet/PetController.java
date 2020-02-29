package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.PetService;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;
    @Autowired
    CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = toPet(petDTO);
        pet = petService.save(pet);
        return toDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable Long petId) {
        return toDTO(petService.findById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> petList = petService.listPets();
        return petList.stream().map(this::toDTO)
                .collect(Collectors.toList());

    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        Customer owner = customerService.findById(ownerId);
        List<Pet> allByOwner = petService.findAllByowner(owner);
        return allByOwner.stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    PetDTO toDTO(Pet pet){
        return modelMapper.map(pet, PetDTO.class);
    }

    Pet toPet(PetDTO petDTO){
        return modelMapper.map(petDTO, Pet.class);
    }

}
