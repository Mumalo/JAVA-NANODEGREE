package com.udacity.jdnd.course3.critter.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter @Setter
public class Person extends BaseEntity {
    private String name;
}
