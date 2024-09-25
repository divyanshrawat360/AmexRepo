package com.customer.ServiceImpl;

import com.customer.Model.Bill;
import com.customer.Model.Customer;
import com.customer.Repository.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerRepo customerRepo;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    void testLoadData() {
        when(customerRepo.saveAll(any())).thenReturn(List.of(new Customer()));
        List<Customer> list = customerService.loadSampleData();
        assertFalse(list.isEmpty());
    }

    @Test
    void getDueBills() {
        when(restTemplate.getForEntity(anyString(), any())).thenReturn(new ResponseEntity<>(new Bill[]{new Bill()}, HttpStatus.OK));
        when(customerRepo.findOne(any())).thenReturn(Optional.of(new Customer()));
        List<Customer> list = customerService.getDueBillsCustomers();
        assertFalse(list.isEmpty());
    }

    @Test
    void getDueBillsFail() {
        when(restTemplate.getForEntity(anyString(), any())).thenReturn(new ResponseEntity<>(new Bill[]{new Bill()}, HttpStatus.INTERNAL_SERVER_ERROR));
        List<Customer> list = customerService.getDueBillsCustomers();
        verify(customerRepo, times(0)).findOne(any());
        assertTrue(list.isEmpty());
    }

    @Test
    void getDueBillsException() {
        when(restTemplate.getForEntity(anyString(), any())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> customerService.getDueBillsCustomers());
    }
}