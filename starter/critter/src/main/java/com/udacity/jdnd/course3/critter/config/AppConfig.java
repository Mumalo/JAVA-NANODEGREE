package com.udacity.jdnd.course3.critter.config;


import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    CustomerService getCustomerService(){
        return new CustomerService();
    }

    @Bean
    PetService getPetService(){
        return new PetService();
    }

    @Bean
    EmployeeService getEmployeeService(){
        return new EmployeeService();
    }

    @Bean
    ScheduleService getSchedule(){
        return new ScheduleService();
    }

    @Bean
    ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}


