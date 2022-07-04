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
    public void addCounterToAllCount(Integer count) {
        allCount += count;
    }

    @Override
    public Integer getCounter() {
        return counter.get();
    }

    @Override
    public Integer getAllCount() {
        return allCount;
    }

    @Override
    public void reset() {
        counter.set(0);
        allCount = 0;
    }

    @Override
    @Scheduled(fixedDelay = 1000)
    public void sendInformationInLogs() {
        Integer result = counter.getAndSet(0);
        addCounterToAllCount(result);
        log.info(result + " requests per second; Total count: " + allCount);
    }
}