package com.billing.ServiceImpl;

import com.billing.Repository.BillingRepo;
import com.billing.Service.BillingService;
import com.billing.model.Bill;
import com.billing.model.BillStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class BillingServiceImplTest {
    @MockBean
    BillingRepo billingRepo;

    BillingService billingService;

    @BeforeEach
    void setup() {
        billingService = new BillingServiceImpl(billingRepo);
    }

    @Test
    void testGetDueBills() {
        when(billingRepo.findAll(any(Example.class))).thenReturn(List.of(new Bill("123", 222, BillStatusEnum.DUE)));
        List<Bill> bills = billingService.getDueBills();
        assertFalse(bills.isEmpty());
    }

    @Test
    void testLoadSampleData() {
        when(billingRepo.saveAll(any())).thenReturn(List.of(new Bill("123", 222, BillStatusEnum.DUE)));
        List<Bill> list = billingService.loadSampleData();
        verify(billingRepo).saveAll(any());
        assertFalse(list.isEmpty());
    }

}