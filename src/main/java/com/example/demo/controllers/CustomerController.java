package com.example.demo.controllers;

import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private final CustomerService logic;

    public CustomerController(CustomerService logic) {
        this.logic = logic;
    }

    @PostMapping("/sortCustomers")
    public ArrayList<Customer> sortCustomersByDueTime(@RequestBody ArrayList<Customer> customers) {
        return logic.sortCustomersByDueDate(customers);
    }
}
