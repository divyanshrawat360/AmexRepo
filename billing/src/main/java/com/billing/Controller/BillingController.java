package com.billing.Controller;

import com.billing.Service.BillingService;
import com.billing.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/billing")
public class BillingController {
    @Autowired
    BillingService billingService;

    @PostMapping
    ResponseEntity<String> saveData(@RequestBody Bill bill) {
        return new ResponseEntity<>(billingService.saveData(bill), HttpStatus.OK);
    }

    @GetMapping(value = "/due-bills")
    ResponseEntity<List<Bill>> getDueBills() {
        return new ResponseEntity<>(billingService.getDueBills(), HttpStatus.OK);
    }

    @GetMapping(value = "/load")
    ResponseEntity<List<Bill>> loadSampleData() {
        return new ResponseEntity<>(billingService.loadSampleData(), HttpStatus.OK);
    }
}
