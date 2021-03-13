package com.example.demo.controllers;

import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    private final ArrayList<Customer> request = new ArrayList<>(Arrays.asList(
            new Customer(10000000, "Ulysses Leon", "2014-06-18T06:26:56-07:00", "2015-04-08T12:47:16-07:00"),
            new Customer(10000001, "Bruce Cardenas", "2013-12-28T14:11:12-08:00", "2014-07-03T21:53:42-07:00"),
            new Customer(10000002, "Barrett Peterson", "2013-12-29T22:33:23-08:00", "2014-10-23T21:46:24-07:00"),
            new Customer(10000003, "Dexter Sweeney", "2014-01-29T11:35:22-08:00", "2014-11-11T08:34:57-08:00")
    ));


    @Before
    public void init() {
        ArrayList<Customer> response = new ArrayList<>(Arrays.asList(
                new Customer(10000001, "Bruce Cardenas", "2013-12-28T14:11:12-08:00", "2014-07-03T21:53:42-07:00"),
                new Customer(10000002, "Barrett Peterson", "2013-12-29T22:33:23-08:00", "2014-10-23T21:46:24-07:00"),
                new Customer(10000003, "Dexter Sweeney", "2014-01-29T11:35:22-08:00", "2014-11-11T08:34:57-08:00"),
                new Customer(10000000, "Ulysses Leon", "2014-06-18T06:26:56-07:00", "2015-04-08T12:47:16-07:00")
        ));
        Mockito.when(customerService.sortCustomersByDueDate(any()))
                .thenReturn(response);
    }

    @Test
    public void requestRespondsWithListOfSortedCustomers() throws Exception {
        mvc.perform(post("/customers/sortCustomers")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].id", is(10000001)))
                .andExpect(jsonPath("$[1].id", is(10000002)))
                .andExpect(jsonPath("$[2].id", is(10000003)))
                .andExpect(jsonPath("$[3].id", is(10000000)));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
