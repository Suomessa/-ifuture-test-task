package com.server.controller;

import com.server.service.AccountServiceImpl;
import com.server.service.MetricServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountServiceController {

    private AccountServiceImpl accountService;

    private MetricServiceImpl metricService;

    @Autowired
    public void setMetricService(MetricServiceImpl metricService) {
        this.metricService = metricService;
    }

    @Autowired
    public void setAccountService(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/getAmount/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAmount(@PathVariable Integer id) {
        String result = String.valueOf(accountService.getAmount(id));
        metricService.increaseCount();
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @PostMapping(path = "/addAmount/{id}")
    public ResponseEntity<String> addAmount(@PathVariable Integer id, @RequestParam Long value) {
        String result = accountService.addAmount(id, value);
        metricService.increaseCount();
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/resetCounter")
    public ResponseEntity<String> reset() {
        metricService.reset();
        return new ResponseEntity<String>("Reset...", HttpStatus.OK);
    }
}
