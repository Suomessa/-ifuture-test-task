package com.server.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class MetricServiceImplTest {

    MetricServiceImpl metricService = new MetricServiceImpl();

    @Test
    public void countsShouldBeNull() {
        Assertions.assertEquals(metricService.getAllCount(), 0);
        Assertions.assertEquals(metricService.getCounter(), 0);
    }

    @Test
    public void shouldIncreaseCounter() {
        Assertions.assertEquals(metricService.getCounter(), 0);
        metricService.increaseCount();
        Assertions.assertEquals(metricService.getCounter(), 1);
    }

    @Test
    public void shouldIncreaseAllCount() {
        Assertions.assertEquals(metricService.getAllCount(), 0);
        metricService.addCounterToAllCount(1);
        Assertions.assertEquals(metricService.getAllCount(), 1);
    }

    @Test
    public void shouldResetCounts() {
        metricService.addCounterToAllCount(1);
        metricService.increaseCount();
        Assertions.assertEquals(metricService.getCounter(), 1);
        Assertions.assertEquals(metricService.getAllCount(), 1);
        metricService.reset();
        Assertions.assertEquals(metricService.getAllCount(), 0);
        Assertions.assertEquals(metricService.getCounter(), 0);
    }
}
