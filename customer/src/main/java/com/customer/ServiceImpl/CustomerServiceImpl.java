package com.customer.ServiceImpl;

import com.customer.Model.Bill;
import com.customer.Model.Customer;
import com.customer.Repository.CustomerRepo;
import com.customer.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Component
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String saveData(Customer customer) {
        return customerRepo.save(customer).getCustomerId();
    }

    @Override
    public List<Customer> getDueBillsCustomers() {
        List<Customer> resultList = new ArrayList<>();
        try {
            ResponseEntity<Bill[]> response
                    = restTemplate.getForEntity(
                    "http://localhost:8081/api/v1/billing/due-bills",
                    Bill[].class);

            if (response.getStatusCode() != HttpStatus.OK) {
                // log error
                return resultList;
            }

            for (Bill bill : Objects.requireNonNull(response.getBody())) {
                Customer cust = new Customer();
                cust.setCustomerBillId(bill.getBillId());
                ExampleMatcher matcher = ExampleMatcher.matching()
                        .withMatcher("customer_bill_id", exact())
                        .withIgnorePaths("customer_id", "customer_name", "mobile_number");
                Optional<Customer> customer = customerRepo.findOne(Example.of(cust, matcher));
                customer.ifPresent(resultList::add);
            }
        } catch (Exception ex) {
            // custom exception can be thrown
            throw new RuntimeException("Not Found");
        }
        return resultList;
    }

    @Override
    public List<Customer> loadSampleData() {
        List<Customer> list = List.of(
                new Customer("customer1", "name1", "number1", "bill1"),
                new Customer("customer2", "name2", "number2", "bill2"),
                new Customer("customer3", "name3", "number3", "bill3"),
                new Customer("customer4", "name4", "number4", "bill4"),
                new Customer("customer5", "name5", "number5", "bill5")
        );
        return customerRepo.saveAll(list);
    }
}
