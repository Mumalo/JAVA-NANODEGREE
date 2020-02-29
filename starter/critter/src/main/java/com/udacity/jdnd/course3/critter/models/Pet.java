package com.udacity.jdnd.course3.critter.models;

import com.udacity.jdnd.course3.critter.pet.PetType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter @Setter
public class Pet extends BaseEntity {

    private PetType type;
    private String name;
    @ManyToOne //many instances of pet can map to an instance of customer
    @JoinColumn(name = "owner_id")
    private Customer owner;
    @OneToMany
    @JoinColumn
    private Set<Schedule> schedules;
    private LocalDate birthDate;
    private String notes;

    @Override
    public String toString() {
        return "Pet{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", schedules=" + schedules +
                ", birthDate=" + birthDate +
                ", notes='" + notes + '\'' +
                '}';
    }
}
