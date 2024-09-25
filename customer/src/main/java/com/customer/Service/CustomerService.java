package com.customer.Service;

import com.customer.Model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    String saveData(Customer customer);

    List<Customer> getDueBillsCustomers();

    List<Customer> loadSampleData();
}
