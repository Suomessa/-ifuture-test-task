package com.server.api;

public interface MetricService {
    void increaseCount();

    void addCounterToAllCount(Integer count);

    Integer getCounter();

    Integer getAllCount();

    void reset();

    void sendInformationInLogs();

}
