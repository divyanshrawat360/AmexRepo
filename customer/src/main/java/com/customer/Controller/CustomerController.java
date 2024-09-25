package com.customer.Controller;

import com.customer.Service.CustomerService;
import com.customer.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping
    ResponseEntity<String> saveData(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.saveData(customer), HttpStatus.OK);
    }

    @GetMapping(value = "/due-bills")
    ResponseEntity<List<Customer>> getDueBillsCustomers() {
        return new ResponseEntity<>(customerService.getDueBillsCustomers(), HttpStatus.OK);
    }

    @GetMapping(value = "/load")
    ResponseEntity<List<Customer>> loadSampleData() {
        return new ResponseEntity<>(customerService.loadSampleData(), HttpStatus.OK);
    }
}
