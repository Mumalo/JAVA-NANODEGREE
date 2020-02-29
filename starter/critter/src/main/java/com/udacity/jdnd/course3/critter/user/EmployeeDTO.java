package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Employee;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.util.Set;

/**
 * Represents the form that employee request and response data takes. Does not map
 * to the database directly.
 */
@Setter @Getter

public class EmployeeDTO {

    @Autowired
    private static ModelMapper modelMapper = new ModelMapper();

    private long id;
    private String name;
    private Set<EmployeeSkill> skills;
    private Set<DayOfWeek> daysAvailable;

    static EmployeeDTO toDTO(Employee employee){
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    static Employee toEmployee(EmployeeDTO employeeDTO){
        return modelMapper.map(employeeDTO, Employee.class);
    }

}
