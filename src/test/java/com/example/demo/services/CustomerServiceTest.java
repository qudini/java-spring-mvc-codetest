package com.example.demo.services;

import com.example.demo.models.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    private final CustomerService customerService = new CustomerService();
    private ArrayList<Customer> customerRequest;


    @Test
    public void customerListIsSortedByDueDate() {
        customerRequest = new ArrayList<>(Arrays.asList(
                new Customer(10000000, "Ulysses Leon", "2014-06-18T06:26:56-07:00", "2015-04-08T12:47:16-07:00"),
                new Customer(10000001, "Bruce Cardenas", "2013-12-28T14:11:12-08:00", "2014-07-03T21:53:42-07:00"),
                new Customer(10000002, "Barrett Peterson", "2013-12-29T22:33:23-08:00", "2014-10-23T21:46:24-07:00"),
                new Customer(10000003, "Dexter Sweeney", "2014-01-29T11:35:22-08:00", "2014-11-11T08:34:57-08:00")
        ));
        ArrayList<Customer> result = customerService.sortCustomersByDueDate(customerRequest);

        assertEquals(10000000, result.get(3).getId());
        assertEquals(10000001, result.get(0).getId());
        assertEquals(10000002, result.get(1).getId());
        assertEquals(10000003, result.get(2).getId());
    }

    @Test
    public void largeIdValuesDoNotFail() {
        customerRequest = new ArrayList<>(Arrays.asList(
                new Customer(1000000000000000000L, "Ulysses Leon", "2014-06-18T06:26:56-07:00", "2015-04-08T12:47:16-07:00"),
                new Customer(1000000000000000001L, "Bruce Cardenas", "2013-12-28T14:11:12-08:00", "2014-07-03T21:53:42-07:00")
        ));
        ArrayList<Customer> result = customerService.sortCustomersByDueDate(customerRequest);
        assertEquals(2, result.size());
    }

}
