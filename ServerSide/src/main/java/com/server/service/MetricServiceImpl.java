package com.server.service;

import com.server.api.MetricService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class MetricServiceImpl implements MetricService {

    private final AtomicInteger counter = new AtomicInteger(0);
    private Integer allCount = 0;

    @Override
    public void increaseCount() {
        counter.incrementAndGet();
    }

    @Override
    public Integer getData() {
        return counter.get();
    }

    @Override
    public void reset() {
        counter.set(0);
        allCount = 0;
    }

    @Scheduled(fixedDelay = 1000)
    public void sendInformationInLogs() {
        Integer result = counter.getAndSet(0);
        allCount+=result;
        log.info(result + " requests per second; Total count: " + allCount);
    }
}