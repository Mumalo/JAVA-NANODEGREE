package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.acl.Owner;
import java.time.LocalDate;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
@Getter @Setter
public class PetDTO {

    @Autowired
    private static ModelMapper MODEL_MAPPER = new ModelMapper();
    private static TypeMap<Pet, PetDTO> DTO_TYPE_MAP = MODEL_MAPPER.createTypeMap(Pet.class, PetDTO.class);
    private static TypeMap<PetDTO, Pet> PET_TYPE_MAP = MODEL_MAPPER.createTypeMap(PetDTO.class, Pet.class);

    @Autowired
    private static CustomerService customerService;

    private long id;
    private PetType type;
    private String name;
    private long ownerId;
    private LocalDate birthDate;
    private String notes;

//   static PetDTO toDTO(Pet pet){
//        DTO_TYPE_MAP.addMapping(Pet::getId, PetDTO::setId)
//                .addMapping(Pet::getName, PetDTO::setName)
//                .addMapping(Pet::getType, PetDTO::setType)
//                .addMapping(Pet::getOwnerId, PetDTO::setOwnerId)
//                .addMapping(Pet::getBirthDate, PetDTO::setBirthDate)
//                .addMapping(Pet::getNotes, PetDTO::setNotes);
//        return DTO_TYPE_MAP.map(pet);
//    }
//
//    static Pet toPet(PetDTO petDTO){
//        Converter<PetDTO, Customer> idToOwnerConverter = ctx -> customerService.findById(ctx.getSource().getOwnerId());
//        PET_TYPE_MAP.addMapping(PetDTO::getId, Pet::setId)
//                .addMapping(PetDTO::getName, Pet::setName)
//                .addMapping(PetDTO::getType, Pet::setType)
//                .addMappings(mapper -> mapper.using(idToOwnerConverter).map(PetDTO::getOwnerId, Pet::setOwner))
//                .addMapping(PetDTO::getBirthDate, Pet::setBirthDate)
//                .addMapping(PetDTO::getNotes, Pet::setNotes);
//        return PET_TYPE_MAP.map(petDTO);
//    }

    @Override
    public String toString() {
        return "PetDTO{" +
                ", id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", ownerId=" + ownerId +
                ", birthDate=" + birthDate +
                ", notes='" + notes + '\'' +
                '}';
    }
}
