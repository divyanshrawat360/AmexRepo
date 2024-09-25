package com.billing.Service;

import com.billing.model.Bill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillingService {
    String saveData(Bill bill);

    List<Bill> getDueBills();

    List<Bill> loadSampleData();
}
