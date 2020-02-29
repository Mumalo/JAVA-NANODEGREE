package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.models.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

//    @Query("SELECT * FROM ")
    List<Employee> findAllBySkillsContainsAndDaysAvailable(EmployeeSkill skill, DayOfWeek dayOfWeek);
}
