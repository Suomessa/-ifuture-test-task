package com.server.api;

public interface MetricService {
    void increaseCount();

    Object getData();

    void reset();
}
