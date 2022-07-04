package com.client.api;

public interface ThreadEngine {
    Runnable createGetRequest();
    Runnable createAddRequest();
}
