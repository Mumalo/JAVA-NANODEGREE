package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Employee;
import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.models.Schedule;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CustomerService customerService;

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAllByPet(Pet pet) {
        return scheduleRepository.findAllByPet(pet);
    }

    public List<Schedule> findAllByEmployee(Long employeeId) {
        Employee employee = employeeService.findById(employeeId);
        return scheduleRepository.findAllByEmployee(employee);
    }

    public List<Schedule> createScheduleForEmpoyees(List<Employee> employees, LocalDate date, Set<EmployeeSkill> skillSet) {
        return employees.stream()
                .map(e -> {
                    Schedule newSchedule = new Schedule();
                    newSchedule.setEmployee(e);
                    newSchedule.setActive(true);
                    newSchedule.setDate(date);
                    newSchedule.setActivities(skillSet);
                    return save(newSchedule);
                }).collect(Collectors.toList());
    }

    public List<Schedule> createScheduleForPets(List<Pet> pets, LocalDate date, Set<EmployeeSkill> skills) {
        return pets.stream()
                .map(p -> {
                    Schedule schedule = new Schedule();
                    schedule.setPet(p);
                    schedule.setActive(true);
                    schedule.setDate(date);
                    schedule.setActivities(skills);
                    return save(schedule);
                }).collect(Collectors.toList());
    }

    public List<Schedule> findAllByCustomer(Long customerId) {
        Customer customer = customerService.findById(customerId);
        List<Pet> pets = petService.findAllByowner(customer);
        List<Schedule> resultList = new ArrayList<>();
        pets.forEach(pet -> {
            List<Schedule> schedulesForPet = findAllByPet(pet);
            resultList.addAll(schedulesForPet);
        });

        return resultList;

    }
}
