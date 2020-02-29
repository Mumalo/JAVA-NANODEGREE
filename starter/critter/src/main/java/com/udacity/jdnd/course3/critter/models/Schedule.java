package com.udacity.jdnd.course3.critter.models;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter @Setter
public class Schedule extends BaseEntity {

    @ManyToOne
    @JoinColumn
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
    private LocalDate date;
    @ElementCollection(targetClass = EmployeeSkill.class)
    private Set<EmployeeSkill> activities;
    private boolean isActive = false;
}
