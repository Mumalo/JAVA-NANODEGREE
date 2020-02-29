package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Employee;
import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.models.Schedule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

//    List<Schedule> findSchedulesByPetsIsContaining(Pet pet);
     List<Schedule> findAllByPet(Pet pet);

     List<Schedule> findAllByEmployee(Employee employee);


}
