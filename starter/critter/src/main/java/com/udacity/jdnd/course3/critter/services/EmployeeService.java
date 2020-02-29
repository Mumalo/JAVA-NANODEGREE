package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.models.Employee;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee instance) {
        return employeeRepository.save(instance);
    }

    public Employee findById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElseThrow(() -> new AssertionError("employee does not exists"));
    }

    public Set<Employee> findAllBySkillAndDate(Set<EmployeeSkill> employeeSkills, LocalDate date) {
        Set<Employee> employees = new HashSet<>();
        employeeSkills.forEach(skill -> {
            employees.addAll(employeeRepository.findAllBySkillsContainsAndDaysAvailable(skill, date.getDayOfWeek()));
        });
        return employees;
    }

    public List<Employee> findAllEmployeesInList(List<Long> emloyeeIds) {
        return emloyeeIds.stream()
                .map(this::findById)
                .collect(Collectors.toList());
    }


}
