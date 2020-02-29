package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.models.Customer;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
@Getter @Setter
public class CustomerDTO {

    @Autowired
    private static ModelMapper modelMapper = new ModelMapper();

    private long id;
    private String name;
    private String phoneNumber;
    private String notes;
    private List<Long> petIds;

    static CustomerDTO toDTO(Customer customer){
        return modelMapper.map(customer, CustomerDTO.class);
    }

    static Customer toCustomer(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO, Customer.class);
    }

}
