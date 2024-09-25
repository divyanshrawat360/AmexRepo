package com.billing.ServiceImpl;

import com.billing.Repository.BillingRepo;
import com.billing.Service.BillingService;
import com.billing.model.Bill;
import com.billing.model.BillStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Component
public class BillingServiceImpl implements BillingService {

    BillingRepo billingRepo;

    @Autowired
    BillingServiceImpl(BillingRepo billingRepo) {
        this.billingRepo = billingRepo;
    }

    @Override
    public String saveData(Bill bill) {
        return billingRepo.save(bill).getBillId();
    }

    @Override
    public List<Bill> getDueBills() {
        Bill bill = new Bill();
        bill.setStatus(BillStatusEnum.DUE);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("status", exact())
                .withIgnorePaths("bill_id", "amount");
        return billingRepo.findAll(Example.of(bill, matcher));
    }

    @Override
    public List<Bill> loadSampleData() {
        List<Bill> list = List.of(
                new Bill("bill1", 123, BillStatusEnum.PAID),
                new Bill("bill2", 1234, BillStatusEnum.DUE),
                new Bill("bill3", 12345, BillStatusEnum.PAID),
                new Bill("bill4", 123456, BillStatusEnum.DUE),
                new Bill("bill5", 1234567, BillStatusEnum.DUE)
        );
        return billingRepo.saveAll(list);
    }
}
