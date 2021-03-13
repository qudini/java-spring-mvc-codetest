package com.example.demo.services;


import com.example.demo.models.Customer;
import org.springframework.stereotype.Service;


import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

@Service
public class CustomerService {

    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ssXXXXX");

    public ArrayList<Customer> sortCustomersByDueDate(ArrayList<Customer> customers) {
        customers.sort(Comparator.comparing((Customer c) -> stringDateToDateTime(c.getDuetime())));
        return customers;
    }

    private OffsetDateTime stringDateToDateTime(String date) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date, DATE_TIME_FORMATTER);
        return offsetDateTime.withOffsetSameInstant(ZoneOffset.UTC);
    }

}
