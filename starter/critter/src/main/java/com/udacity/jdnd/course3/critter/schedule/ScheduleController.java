package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Employee;
import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.models.Schedule;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Handles web requests related to Schedule.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(path = "/schedule")
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
            List<Employee> allEmployeesInList = employeeService.findAllEmployeesInList(scheduleDTO.getEmployeeIds());
            List<Pet> allPetsInList = petService.findAllPetsInList(scheduleDTO.getPetIds());
            scheduleService.createScheduleForEmpoyees(allEmployeesInList, scheduleDTO.getDate(), scheduleDTO.getActivities());
            scheduleService.createScheduleForPets(allPetsInList, scheduleDTO.getDate(), scheduleDTO.getActivities());
            return scheduleDTO;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        Pet pet = petService.getById(petId);
        List<Schedule> schedules = scheduleService.findAllByPet(pet);
        return schedules.stream()
                .map(this::toScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.findAllByEmployee(employeeId);
        return schedules.stream()
                .map(this::toScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.findAllByCustomer(customerId);
        return schedules.stream()
                .map(this::toScheduleDTO)
                .collect(Collectors.toList());
    }

    private ScheduleDTO toScheduleDTO(Schedule schedule){
        return modelMapper.map(schedule, ScheduleDTO.class);
    }

}
