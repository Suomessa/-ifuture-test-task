package com.client;

import com.client.api.ThreadEngine;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class ThreadEngineImpl implements ThreadEngine {
    private Integer rCount = 0;
    private Integer wCount = 0;

    private Runnable getRequest;
    private Runnable addRequest;

    private RequestFactoryImpl requestFactory;

    private List<Integer> IDs;

    private final OkHttpClient client = new OkHttpClient.Builder().build();

    public ThreadEngineImpl(Integer rCount, Integer wCount, List<Integer> IDs, String URL) {
        this.rCount = rCount;
        this.wCount = wCount;
        this.requestFactory = new RequestFactoryImpl(URL);
        this.IDs = IDs;

        getRequest = () -> {
            Request request = this.requestFactory.prepareGetRequest(this.IDs.get((int) (Math.random() * IDs.size())));
            try {
                Response response = this.client.newCall(request).execute();
                System.out.println(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            };
        };

        addRequest = () -> {
            Request request = this.requestFactory.prepareAddRequest(this.IDs.get((int) (Math.random() * IDs.size())));
            try {
                Response response = this.client.newCall(request).execute();
                System.out.println(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            };
        };
    }

    public Runnable createGetRequest() {
        return getRequest;
    }

    public Runnable createAddRequest() {
        return addRequest;
    }
}
