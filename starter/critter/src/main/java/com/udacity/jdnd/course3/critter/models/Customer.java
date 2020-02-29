package com.udacity.jdnd.course3.critter.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter @Setter
public class Customer extends Person {

    private String phoneNumber;
    private String notes;
    @OneToMany
    @JoinColumn
    private List<Pet> pets;

    @OneToMany
    @JoinColumn
    private List<Schedule> schedules;
}
